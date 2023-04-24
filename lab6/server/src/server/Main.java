package server;

import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.network.NetworkServer;
import server.network.TCPServer;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try {
            Executor executor = new Executor();
            QueryHandler queryHandler = new QueryHandler();
            TCPServer server = new TCPServer();
            server.openConnection();

        } catch (InvalidFileDataException | FileNotFoundException | FilePermissionException e) {
            throw new RuntimeException(e);
        }
    }
}
