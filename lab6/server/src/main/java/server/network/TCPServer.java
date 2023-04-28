package server.network;

import common.IOHandlers.BufferedConsoleReader;
import jdk.net.ExtendedSocketOptions;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import common.IOHandlers.BasicReader;
import common.IOHandlers.ScannerConsoleReader;
import common.exceptions.InvalidCommandException;
import common.exceptions.WrongNumberOfArgumentsException;
import common.requests.ExitRequest;
import common.requests.Request;
import common.requests.SaveRequest;
import common.responses.EmptyResponse;
import common.responses.ServerErrorResponse;
import common.responses.Response;
import server.handlers.ClientCommandHandler;
import server.exceptions.InvalidRequestException;
import server.handlers.Executor;
import server.handlers.ServerCommandHandler;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TCPServer implements NetworkServer {
    private static final int BUFFER_SIZE = 4096;
    private final ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    private ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private final ClientCommandHandler clientCommandHandler;
    private final ServerCommandHandler serverCommandHandler;

    private boolean canExit = false;
    private final BasicReader reader = new ScannerConsoleReader();
    private static final Logger logger = (Logger) LoggerFactory.getLogger("server.network");

    public TCPServer(Executor executor) throws IOException {
        this.clientCommandHandler = new ClientCommandHandler(executor);
        this.serverCommandHandler = new ServerCommandHandler(executor, this);
        logger.debug("Logger initialized");

        this.openConnection();
        this.selector = initSelector();

        System.out.println(
                "Server app launched.\n" +
                "You can use save and exit commands in interactive mode.");

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
            while (!canExit) {
                selector.selectNow();
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

                try {
                    BufferedConsoleReader reader = new BufferedConsoleReader();

                    if (reader.ready()) {
                        String input = reader.readLine().trim();
                        if (input.startsWith("//") || input.equals("")) {
                            continue;
                        }
                        String[] inputArray = input.split(" +");
                        String commandName = inputArray[0].toLowerCase();

                        String[] args = new String[inputArray.length - 1];
                        System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);

                        switch (commandName) {
                            case "save" -> {
                                if (args.length != 0)
                                    throw new WrongNumberOfArgumentsException();

                                Request request = new SaveRequest();
                                serverCommandHandler.handle(request);
                                logger.info("Collection saved successfully");
                                System.out.println("*collection saved successfully*");
                            }
                            case "exit" -> {
                                if (args.length != 0)
                                    throw new WrongNumberOfArgumentsException();

                                Request request = new ExitRequest();
                                serverCommandHandler.handle(request);
                            }
                            default -> throw new InvalidCommandException(commandName);
                        }
                    }
                } catch (WrongNumberOfArgumentsException | InvalidCommandException e) {
                    System.out.println(e.getMessage());
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
            response = clientCommandHandler.handle(request);
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

    @Override
    public Response exit() {
        canExit = true;
        return new EmptyResponse();
    }
}
