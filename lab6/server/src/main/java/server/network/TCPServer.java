package server.network;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import common.requests.Request;
import common.responses.ServerErrorResponse;
import common.responses.Response;
import jdk.net.ExtendedSocketOptions;
import org.slf4j.LoggerFactory;
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

public class TCPServer {
    private static final int BUFFER_SIZE = 4096;
    private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    private ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private final CommandHandler commandHandler;

    private static final Logger logger = (Logger) LoggerFactory.getLogger("server.network");

    public TCPServer(CommandHandler commandHandler) throws IOException {
        this.commandHandler = commandHandler;
        logger.setLevel(Level.INFO);
        logger.debug("Logger initialized");

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
            String message = "Unable to use address " + address.getHostName() + ":" + address.getPort() + " - " + e.getMessage();
            logger.warn(message);
            throw new BindException(message);
        }
        logger.info("TCP Server initialized");
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

        infoClient(socketChannel, "connected");
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        readBuffer.clear();
        int bytesRead;
        try {
            bytesRead = sc.read(readBuffer);
        } catch (IOException e) {
            infoClient(sc, "forceful shutdown");
            key.cancel();
            sc.close();
            return;
        }
        if (bytesRead == -1) {
            infoClient(sc, "graceful shutdown");
            key.cancel();
            return;
        }
        String input = new String(readBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
        debugClient(sc, "input:\n" + input);

        Response response;
        try {
            response = handleRequest(readBuffer);
        } catch (ClassNotFoundException e) {
            response = new ServerErrorResponse(e.getMessage());
            debugClient(sc, "Unknown command");
        }
        sc.register(selector, SelectionKey.OP_WRITE, response);

//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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
            String message = "Unknown request type";
            logger.debug(message);
            throw new ClassNotFoundException("Unknown request type");
        } catch (InvalidRequestException e) {
            logger.debug(e.getMessage());
            response = new ServerErrorResponse(e.getMessage());
        }

        return response;
    }

    private void write(SelectionKey key) throws IOException {
        // Get socket channel and response
        SocketChannel sc = (SocketChannel) key.channel();
        Response response = (Response) key.attachment();

        ByteBuffer writeBuffer = serializeObject(response);
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            sc.write(writeBuffer);
        }

        debugClient(sc, "write response " + response.name);

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
            String message = "Unknown request";
            logger.debug(message);
            throw new InvalidRequestException(message);
        } catch (EOFException e) {
            String message = "Request is too big";
            logger.debug(message);
            throw new InvalidRequestException(message);
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
            logger.warn("Unable to close server socket channel: {}", e.getMessage());
        }
    }

    private void debugClient(SocketChannel sc, String message) throws IOException {
        logger.debug("Client {} - {}", sc.getRemoteAddress(), message);
    }

    private void infoClient(SocketChannel sc, String message) throws IOException {
        logger.debug("Client {} - {}", sc.getRemoteAddress(), message);
    }

    private void warnClient(SocketChannel sc, String message) throws IOException {
        logger.debug("Client {} - {}", sc.getRemoteAddress(), message);
    }
}
