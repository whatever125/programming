package client.consoleClient;

import client.AbstractClient;
import client.Client;
import client.Invoker;
import client.exceptions.*;
import client.commands.*;
import client.network.NetworkClient;
import client.network.TCPClient;
import common.IOHandlers.BasicReader;
import common.IOHandlers.ConsolePasswordReader;
import common.IOHandlers.ScannerConsoleReader;
import common.IOHandlers.ScriptReader;
import common.exceptions.*;
import common.models.*;
import common.models.helpers.MovieArgumentChecker;
import common.responses.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

import static client.consoleClient.MovieDataConsoleReader.*;

/**
 The ConsoleClient class implements the Client interface and is responsible for
 interacting with the user through the console. It initializes the Invoker and Receiver,
 reads user input, executes commands and handles exceptions.
 */
public class ConsoleClient extends AbstractClient {
    // TODO: 16/5/2023 replace reader
    BasicReader consoleReader = new ScannerConsoleReader();
//    BasicReader passwordReader = new ConsolePasswordReader();
    BasicReader passwordReader = new ScannerConsoleReader();

    /**
     * Main method of the ConsoleClient class. Initializes the Invoker, Receiver and Reader and
     * starts an interactive loop to read user input and execute commands.
     */
    @Override
    public void run() {
        try {
            // Initialize Invoker, Receiver and Reader
            networkClient.openConnection();
            System.out.println("Successfully connected to server");
            BasicReader consoleReader = new ScannerConsoleReader();

            boolean loggedIn = false;
            while (!loggedIn) {
                try {
                    loggedIn = entranceForm();
                } catch (CustomIOException | AuthenticationException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Data loaded successfully. You are now in interactive mode\nType 'help' to see the list of commands\n");

            while (!canExit) {
                try {
                    String message = readAndExecuteCommand(consoleReader);
                    System.out.println(message);
                } catch (InvalidCommandException | WrongNumberOfArgumentsException | WrongArgumentException |
                         InvalidScriptException | CustomIOException | ErrorResponseException | NetworkClientException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (EndOfInputException e) {
            // Handle exceptions thrown when there is a problem with the data file or the user input
            System.out.println(e.getMessage());
            networkClient.silentCloseConnection();
            System.exit(-1);
        } catch (NetworkClientException e) {
            System.out.println(e.getMessage());
            networkClient.silentCloseConnection();
            System.exit(-1);
        }
    }

    private boolean entranceForm() throws AuthenticationException, NetworkClientException, CustomIOException {
        List<String> login = Arrays.asList("login", "l", "1");
        List<String> register = Arrays.asList("register", "r", "2");
        while (true) {
            String type = consoleReader.readLine("Login (l) or Register (r)?").trim().toLowerCase();
            if (login.contains(type)) {
                getUserCredentialsForm();
                authenticateUser();
                return true;
            } else if (register.contains(type)) {
                getUserCredentialsForm();
                registerUser();
                return true;
            }
        }
    }

    private void getUserCredentialsForm() throws AuthenticationException {
        login = consoleReader.readLine("Enter login").trim();
        if (login.equals("")) {
            throw new AuthenticationException("Login cannot be empty");
        }
        password = passwordReader.readLine("Enter password").trim();
    }

    private void authenticateUser() throws AuthenticationException, NetworkClientException, CustomIOException {
        Command command = new AuthenticateUserCommand(this, networkClient, login, password);
        Response abstractResponse = command.execute();
        try {
            handleErrorResponse(abstractResponse);
        } catch (ErrorResponseException e) {
            throw new AuthenticationException(e.getMessage());
        }
        AuthenticateUserResponse response = (AuthenticateUserResponse) abstractResponse;
        if (response.error != null) {
            throw new AuthenticationException(abstractResponse.error);
        }
    }

    private void registerUser() throws AuthenticationException, NetworkClientException, CustomIOException {
        Command command = new AddUserCommand(this, networkClient, login, password);
        Response abstractResponse = command.execute();
        try {
            handleErrorResponse(abstractResponse);
        } catch (ErrorResponseException e) {
            throw new AuthenticationException(e.getMessage());
        }
        AddUserResponse response = (AddUserResponse) abstractResponse;
        if (response.error != null) {
            throw new AuthenticationException(abstractResponse.error);
        }
    }

    /**
     * Executes a script file containing a list of commands.
     *
     * @param path the path of the script file to execute
     * @throws CustomIOException if there is an error reading the script file
     */
    @Override
    public Response executeScript(String path) throws CustomIOException {
        try {
            if (pathStackContains(path))
                throw new FileRecursionError(path);

            BasicReader basicReader = new ScriptReader(path);
            pathStack.push(path);
            int lineCounter = 0;
            while (basicReader.hasNextLine()) {
                try {
                    lineCounter += 1;
                    String message = readAndExecuteCommand(basicReader);
                    handleExecuteOutput(message);
                } catch (InvalidCommandException | WrongNumberOfArgumentsException | WrongArgumentException |
                         InvalidScriptException | ErrorResponseException | NetworkClientException e) {
                    String message = printPathStack() + ":" + lineCounter + ": " + e.getMessage();
                    System.out.println(message);
                    return new ErrorResponse(message);
                }
            }
            pathStack.pop();
        } catch (FileRecursionError | FileNotFoundException | FilePermissionException e) {
            System.out.println(e.getMessage());
            return new ErrorResponse(e.getMessage());
        }
        return new EmptyResponse();
    }

    private void handleExecuteOutput(String message) {
        System.out.println(message);
    }
}
