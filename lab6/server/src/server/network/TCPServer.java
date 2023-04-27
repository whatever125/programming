package server.network;

import common.requests.Request;
import common.responses.ServerErrorResponse;
import common.responses.Response;
import jdk.net.ExtendedSocketOptions;
import server.CommandHandler;
import server.exceptions.InvalidRequestException;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TCPServer {
    private static final int BUFFER_SIZE = 4096;
    private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private final CommandHandler commandHandler;

    public TCPServer(CommandHandler commandHandler) throws IOException {
        this.commandHandler = commandHandler;

        this.openConnection();
        this.selector = initSelector();
        this.run();
    }

    private void openConnection() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.setOption(ExtendedSocketOptions.TCP_KEEPIDLE, 10);
        InetSocketAddress address = new InetSocketAddress(HOST, PORT);
        try {
            serverSocketChannel.bind(address);
        } catch (BindException e) {
            throw new BindException("Unable to use address " + address.getHostName() + ":" + address.getPort() + " - " + e.getMessage());
        }
    }

    private Selector initSelector() throws IOException {
        Selector socketSelector = SelectorProvider.provider().openSelector();
        serverSocketChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
        return socketSelector;
    }

    private void run() throws IOException {
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            accept(key);
                        } else if (key.isReadable()) {
                            read(key);
                        } else if (key.isWritable()) {
                            write(key);
                        }
                    }
                }
            }
        } finally {
            silentCloseConnection();
        }
    }

    private void accept(SelectionKey key) throws IOException {
        var ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("accept: Client " + socketChannel.getRemoteAddress() + " is connected");
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
            return;
        }
        if (bytesRead == -1) {
            System.out.println("read: Graceful shutdown");
            System.out.println();
            key.cancel();
            return;
        }
        String input = new String(readBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
        System.out.println("read: " + input);

        Response response;
        try {
            response = handleRequest(readBuffer);
        } catch (ClassNotFoundException e) {
            response = new ServerErrorResponse(e.getMessage());
            System.out.println("unknown command");
        }
        sc.register(selector, SelectionKey.OP_WRITE, response);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Response handleRequest(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        Request request;
        Response response;
        try {
            request = (Request) deserializeObject(buffer);
            if (request == null) {
                throw new InvalidRequestException("Request is null");
            }
            response = commandHandler.handle(request);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Unknown request type");
        } catch (InvalidRequestException e) {
            response = new ServerErrorResponse(e.getMessage());
        }

        return response;
    }

    private void write(SelectionKey key) throws IOException {
        // Get socket channel and response
        SocketChannel sc = (SocketChannel) key.channel();
        Response response = (Response) key.attachment();

        System.out.println("write: " + response.name + "\n" + "write: " + response.error);

        ByteBuffer writeBuffer = serializeObject(response);
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            sc.write(writeBuffer);
        }
        System.out.println("Response sent");

        sc.register(selector, SelectionKey.OP_READ);
    }

    private ByteBuffer serializeObject(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ByteBuffer buffer = ByteBuffer.allocate(baos.size());
        buffer.put(baos.toByteArray());
        return buffer;
    }

    private Object deserializeObject(ByteBuffer buffer) throws IOException, ClassNotFoundException, InvalidRequestException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        }  catch (StreamCorruptedException e) {
            throw new InvalidRequestException("Unknown request");
        } catch (EOFException e) {
            throw new InvalidRequestException("Request is too big");
        }
    }

    private void closeConnection() throws IOException {
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }
    }

    private void silentCloseConnection() {
        try {
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to close server socket channel: " + e.getMessage());
        }
    }
}
