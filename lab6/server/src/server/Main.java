package server;

import common.requests.SaveRequest;
import server.commands.Command;
import server.commands.Save;
import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.network.TCPServer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Executor executor = new Executor();
            CommandHandler commandHandler = new CommandHandler(executor);
            TCPServer server = new TCPServer(commandHandler);
            Command save = new Save(executor);
            save.execute(new SaveRequest());

        } catch (InvalidFileDataException | FileNotFoundException | FilePermissionException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
//            System.out.println("! Server IO exception: " + e.getMessage() + " !");
            throw new RuntimeException(e);
//            System.exit(-1);
        }
    }
}
