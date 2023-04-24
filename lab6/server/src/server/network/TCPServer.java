package server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TCPServer implements NetworkServer{
    private ServerSocketChannel serverSocketChannel;
    private static final int BUFFER_SIZE = 4096;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    private final Selector selector;

    public TCPServer() {
        this.openConnection();
        this.selector = initSelector();
        this.loop();
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

    private void loop() {
        while (true) {
            try{
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        System.out.println("accept");
                        accept(key);
                    } else if (key.isReadable()) {
                        System.out.println("read");
                        read(key);

                    } else if (key.isWritable()) {
//                        System.out.println("write");
//                        write(key);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        var ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("Client is connected");
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
            System.out.println("Forceful shutdown");
            return;
        }
        if (bytesRead == -1) {
            System.out.println("Graceful shutdown");
            key.channel().close();
            key.cancel();
            return;
        }
        System.out.println(new String(readBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8));
        sc.register(selector, SelectionKey.OP_WRITE);
    }

    @Override
    public void openConnection() {
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

    @Override
    public void closeConnection() {
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    @Override
    public ByteBuffer receiveData() {
        try {
            readBuffer.clear();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.read(readBuffer);
            return readBuffer;
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }

    @Override
    public int sendData(byte[] bytesToSend) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytesToSend);

            SocketChannel socketChannel = serverSocketChannel.accept();
            int numberOfWrittenBytes = socketChannel.write(byteBuffer);
            assert numberOfWrittenBytes != -1; // todo
            return numberOfWrittenBytes;
        } catch (IOException e) {
            // todo
            throw new RuntimeException(e);
        }
    }
}
