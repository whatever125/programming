package server.network;

import common.requests.Request;
import common.responses.Response;
import server.CommandHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TCPServer {
    private static final int BUFFER_SIZE = 4096;
    private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    private final ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    private ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private final CommandHandler commandHandler;

    public TCPServer(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;

        this.openConnection();
        this.selector = initSelector();
        this.run();
    }

    private void openConnection() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            InetSocketAddress address = new InetSocketAddress(HOST, PORT);
            serverSocketChannel.bind(address);
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    private Selector initSelector() {
        try {
            Selector socketSelector = SelectorProvider.provider().openSelector();
            serverSocketChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
            return socketSelector;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void run() {
        try{
            while (true) {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    private void accept(SelectionKey key) throws IOException {
        var ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("accept: Client is connected");
        System.out.println();
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        readBuffer.clear();
        int bytesRead;
        try {
            bytesRead = sc.read(readBuffer);
        } catch (IOException e) {
            key.cancel();
            sc.close();
            System.out.println("read: Forceful shutdown");
            System.out.println();
            return;
        }
        if (bytesRead == -1) {
            System.out.println("read: Graceful shutdown");
            System.out.println();
            key.channel().close();
            key.cancel();
            return;
        }
        String input = new String(readBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
        System.out.println("read: " + input);
        System.out.println("\\read");
        System.out.println();

        Response response = handleInput(readBuffer.array());

        sc.register(selector, SelectionKey.OP_WRITE, response);
    }

    private Response handleInput(byte[] input) throws IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(input));
            Request request = (Request) ois.readObject();
            ois.close();

            Response response = commandHandler.handle(request);
            return response;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(SelectionKey key) throws IOException {
        // Get socket channel and response
        SocketChannel sc = (SocketChannel) key.channel();
        Response response = (Response) key.attachment();

        System.out.println("write: " + response.name + "\n" + "write: " + response.error);
        System.out.println();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);

        writeBuffer.clear();
        writeBuffer.put(baos.toByteArray());
        writeBuffer.flip();

        while (writeBuffer.hasRemaining()) {
            sc.write(writeBuffer);
        }

        System.out.println("Response sent");

        sc.register(selector, SelectionKey.OP_READ);
    }

    private void closeConnection() {
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }
}
