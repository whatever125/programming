package server;

import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.network.TCPServer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Executor executor = new Executor();
            CommandHandler commandHandler = new CommandHandler(executor);
            TCPServer server = new TCPServer(commandHandler);

        } catch (InvalidFileDataException | FileNotFoundException | FilePermissionException e) {
            throw new RuntimeException(e);
        }
    }
}
