package server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import common.requests.SaveRequest;
import org.slf4j.LoggerFactory;
import server.commands.Command;
import server.commands.Save;
import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
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
            CommandHandler commandHandler = new CommandHandler(executor);
            TCPServer server = new TCPServer(commandHandler);
            Command save = new Save(executor);
            save.execute(new SaveRequest());
            logger.info("Server app stopped");
        } catch (InvalidFileDataException | FileNotFoundException | FilePermissionException e) {
            logger.error("Fatal error: {}", e.getMessage(), e);
            System.exit(-1);
        } catch (IOException e) {
            logger.error("Fatal error: {}", e.getMessage(), e);
            System.exit(-1);
        }
    }
}
