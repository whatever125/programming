package server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.exceptions.EnvironmentVariableException;
import server.handlers.Executor;
import server.network.TCPServer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private static final Logger logger = (Logger) LoggerFactory.getLogger("server");

    public static void main(String[] args) {
        try {
            logger.setLevel(Level.INFO);
            logger.info("Server app launched");
            Executor executor = new Executor();
            TCPServer server = new TCPServer(executor);
            logger.info("Server app stopped");
        } catch (InvalidFileDataException | FileNotFoundException | FilePermissionException |
                 EnvironmentVariableException e) {
            logger.error("Fatal error: {}", e.getMessage(), e);
            System.exit(-1);
        } catch (IOException e) {
            logger.error("Fatal error: {}", e.getMessage(), e);
            System.exit(-1);
        }
    }
}
