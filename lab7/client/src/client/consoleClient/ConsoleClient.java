package client.consoleClient;

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
import org.w3c.dom.ls.LSOutput;

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
public class ConsoleClient implements Client {
    private Invoker invoker;
    private NetworkClient networkClient;
    private final Stack<String> pathStack = new Stack<>();
    private boolean canExit = false;

    private static final String HOST = "localhost";
    private static final int PORT = 3683;

    private String login;
    private String password;

    // TODO: 16/5/2023 replace reader
    BasicReader consoleReader = new ScannerConsoleReader();
    BasicReader passwordReader = new ConsolePasswordReader();
//    BasicReader passwordReader = new ScannerConsoleReader();

    /**
     * Main method of the ConsoleClient class. Initializes the Invoker, Receiver and Reader and
     * starts an interactive loop to read user input and execute commands.
     */
    public void run() {
        try {
            // Initialize Invoker, Receiver and Reader
            invoker = new Invoker();
            networkClient = new TCPClient(HOST, PORT);
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
                    readAndExecuteCommand(consoleReader);
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
            throw new AuthenticationException("! Login cannot be empty !");
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
            throw new AuthenticationException("! " + abstractResponse.error + " !");
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
            throw new AuthenticationException("! " + abstractResponse.error + " !");
        }
    }

    /**
     * Reads a command from the user and executes it using the Invoker and Receiver.
     *
     * @param basicReader the reader used to read input from the user.
     * @throws InvalidCommandException if the user enters an invalid command.
     * @throws WrongNumberOfArgumentsException if the user enters a command with the wrong number of arguments.
     * @throws WrongArgumentException if the user enters a command with invalid arguments.
     * @throws InvalidScriptException if the user enters an invalid script file.
     * @throws CustomIOException if there is an IO error when reading the user input.
     */
    private void readAndExecuteCommand(BasicReader basicReader) throws InvalidCommandException,
            WrongNumberOfArgumentsException, WrongArgumentException, InvalidScriptException, CustomIOException,
            ErrorResponseException, NetworkClientException {
        String input = basicReader.readLine().trim();
        if (input.startsWith("//") || input.equals("")) {
            return;
        }
        String[] inputArray = input.split(" +");
        String commandName = inputArray[0].toLowerCase();

        String[] args = new String[inputArray.length - 1];
        System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);

        switch (commandName) {
            case "help" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Help(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
            }
            case "history" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new History(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
            }
            case "info" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Info(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                InfoResponse response = (InfoResponse) abstractResponse;
                if (response.error == null) {
                    System.out.println(response.info);
                } else {
                    System.out.println(response.error);
                }
            }
            case "show" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Show(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                ShowResponse response = (ShowResponse) abstractResponse;
                if (response.error == null) {
                    HashMap<Integer, Movie> movieHashMap = response.getMovieHashMap();
                    PrettyPrinter.printMovieHashMap(movieHashMap);
                } else {
                    System.out.println(response.error);
                }
            }
            case "insert" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer key = Integer.parseInt(args[0]);
                    MovieArgumentChecker.checkKey(key);
                    String movieName = readMovieName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Integer y = readY(basicReader, inScriptMode);
                    long oscarsCount = readOscrasCount(basicReader, inScriptMode);
                    MovieGenre movieGenre = readMovieGenre(basicReader, inScriptMode);
                    MpaaRating mpaaRating = readMpaaRating(basicReader, inScriptMode);
                    String directorName = readDirectorName(basicReader, inScriptMode);
                    LocalDateTime birthday = readBirthday(basicReader, inScriptMode);
                    Integer weight = readWeight(basicReader, inScriptMode);
                    String passportID = readPassportID(basicReader, inScriptMode);

                    AbstractCommand command = new Insert(this, networkClient, login, password, key, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID);
                    Response abstractResponse = invoker.execute(command);
                    handleErrorResponse(abstractResponse);
                    InsertResponse response = (InsertResponse) abstractResponse;
                    if (response.error == null) {
                        System.out.println("*element added successfully*");
                    } else {
                        System.out.println(response.error);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "update" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer id = Integer.parseInt(args[0]);
                    String movieName = readMovieName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Integer y = readY(basicReader, inScriptMode);
                    long oscarsCount = readOscrasCount(basicReader, inScriptMode);
                    MovieGenre movieGenre = readMovieGenre(basicReader, inScriptMode);
                    MpaaRating mpaaRating = readMpaaRating(basicReader, inScriptMode);
                    String directorName = readDirectorName(basicReader, inScriptMode);
                    LocalDateTime birthday = readBirthday(basicReader, inScriptMode);
                    Integer weight = readWeight(basicReader, inScriptMode);
                    String passportID = readPassportID(basicReader, inScriptMode);

                    AbstractCommand command = new Update(this, networkClient, login, password, id, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID);
                    Response abstractResponse = invoker.execute(command);
                    handleErrorResponse(abstractResponse);
                    UpdateResponse response = (UpdateResponse) abstractResponse;
                    if (response.error == null) {
                        System.out.println("*element updated successfully*");
                    } else {
                        System.out.println(response.error);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "remove_key" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    MovieArgumentChecker.checkKey(key);

                    AbstractCommand command = new RemoveKey(this, networkClient, login, password, key);
                    Response abstractResponse = invoker.execute(command);
                    handleErrorResponse(abstractResponse);
                    RemoveKeyResponse response = (RemoveKeyResponse) abstractResponse;
                    if (response.error == null) {
                        System.out.println("*element removed successfully*");
                    } else {
                        System.out.println(response.error);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "clear" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Clear(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                System.out.println("*movies created by '" + login + "' cleared successfully*");
            }
            case "execute_script" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                String path = args[0];

                AbstractCommand command = new ExecuteScript(this, networkClient, login, password, path);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
            }
            case "exit" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Exit(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
            }
            case "remove_greater" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                boolean inScriptMode = inScriptMode();
                String movieName = readMovieName(basicReader, inScriptMode);
                Integer x = readX(basicReader, inScriptMode);
                Integer y = readY(basicReader, inScriptMode);
                long oscarsCount = readOscrasCount(basicReader, inScriptMode);
                MovieGenre movieGenre = readMovieGenre(basicReader, inScriptMode);
                MpaaRating mpaaRating = readMpaaRating(basicReader, inScriptMode);
                String directorName = readDirectorName(basicReader, inScriptMode);
                LocalDateTime birthday = readBirthday(basicReader, inScriptMode);
                Integer weight = readWeight(basicReader, inScriptMode);
                String passportID = readPassportID(basicReader, inScriptMode);

                AbstractCommand command = new RemoveGreater(this, networkClient, login, password, movieName, x, y, oscarsCount,
                        movieGenre, mpaaRating, directorName, birthday, weight, passportID);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                RemoveGreaterResponse response = (RemoveGreaterResponse) abstractResponse;
                if (response.error == null) {
                    if (response.count == 0) {
                        System.out.println("*no elements removed*");
                    } else {
                        System.out.println("* " + response.count + " elements removed successfully*");
                    }
                } else {
                    System.out.println(response.error);
                }
            }
            case "replace_if_lowe" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer key = Integer.parseInt(args[0]);
                    MovieArgumentChecker.checkKey(key);
                    String movieName = readMovieName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Integer y = readY(basicReader, inScriptMode);
                    long oscarsCount = readOscrasCount(basicReader, inScriptMode);
                    MovieGenre movieGenre = readMovieGenre(basicReader, inScriptMode);
                    MpaaRating mpaaRating = readMpaaRating(basicReader, inScriptMode);
                    String directorName = readDirectorName(basicReader, inScriptMode);
                    LocalDateTime birthday = readBirthday(basicReader, inScriptMode);
                    Integer weight = readWeight(basicReader, inScriptMode);
                    String passportID = readPassportID(basicReader, inScriptMode);

                    AbstractCommand command = new ReplaceIfLowe(this, networkClient, login, password, key, movieName, x, y,
                            oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID);
                    Response abstractResponse = invoker.execute(command);
                    handleErrorResponse(abstractResponse);
                    ReplaceIfLoweResponse response = (ReplaceIfLoweResponse) abstractResponse;
                    if (response.error == null) {
                        if (response.replaced) {
                            System.out.println("*element replaced successfully*");
                        } else {
                            System.out.println("*element was not replaced*");
                        }
                    } else {
                        System.out.println(response.error);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "remove_lower_key" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    MovieArgumentChecker.checkKey(key);

                    AbstractCommand command = new RemoveLowerKey(this, networkClient, login, password, key);
                    Response abstractResponse = invoker.execute(command);
                    handleErrorResponse(abstractResponse);
                    RemoveLowerKeyResponse response = (RemoveLowerKeyResponse) abstractResponse;
                    if (response.error == null) {
                        if (response.count == 0) {
                            System.out.println("*no elements removed*");
                        } else {
                            System.out.println("* " + response.count + " elements removed successfully*");
                        }
                    } else {
                        System.out.println(response.error);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "print_ascending" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new PrintAscending(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                PrintAscendingResponse response = (PrintAscendingResponse) abstractResponse;
                if (response.error == null) {
                    System.out.println("*elements of collection ascended*");
                    List<Movie> movieList = response.movieList;
                    PrettyPrinter.printMovieList(movieList);
                } else {
                    System.out.println(response.error);
                }
            }
            case "print_descending" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new PrintDescending(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                PrintDescendingResponse response = (PrintDescendingResponse) abstractResponse;
                if (response.error == null) {
                    System.out.println("*elements of collection descended*");
                    List<Movie> movieList = response.movieList;
                    PrettyPrinter.printMovieList(movieList);
                } else {
                    System.out.println(response.error);
                }
            }
            case "print_field_descending_oscars_count" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new PrintFieldDescendingOscarsCount(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                PrintFieldDescendingOscarsCountResponse response = (PrintFieldDescendingOscarsCountResponse) abstractResponse;
                if (response.error == null) {
                    System.out.println("*oscars count descended*");
                    List<Movie> movieList = response.movieList;
                    PrettyPrinter.printMovieListOscars(movieList);
                } else {
                    System.out.println(response.error);
                }
            }

            default -> throw new InvalidCommandException(commandName);
        }
    }

    public void handleErrorResponse(Response response) throws ErrorResponseException {
        if (Objects.equals(response.name, "error")) {
            throw new ErrorResponseException(response.error);
        }
    }

    /**
     * Displays a list of available commands and their descriptions to the user.
     */
    @Override
    public Response help() {
        System.out.println("*list of commands*");
        System.out.printf("%-37s", "- help");
        System.out.println(" : вывести справку по доступным командам");
        System.out.printf("%-37s", "- info");
        System.out.println(" : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.printf("%-37s", "- show");
        System.out.println(" : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.printf("%-37s", "- insert null {element}");
        System.out.println(" : добавить новый элемент с заданным ключом");
        System.out.printf("%-37s", "- update id {element}");
        System.out.println(" : обновить значение элемента коллекции, id которого равен заданному");
        System.out.printf("%-37s", "- remove_key null");
        System.out.println(" : удалить элемент из коллекции по его ключу");
        System.out.printf("%-37s", "- clear");
        System.out.println(" : очистить коллекцию");
        System.out.printf("%-37s", "- execute_script file_name");
        System.out.println(" : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.printf("%-37s", "- exit");
        System.out.println(" : завершить программу (без сохранения в файл)");
        System.out.printf("%-37s", "- remove_greater {element}");
        System.out.println(" : удалить из коллекции все элементы, превышающие заданный");
        System.out.printf("%-37s", "- replace_if_lowe null {element}");
        System.out.println(" : заменить значение по ключу, если новое значение меньше старого");
        System.out.printf("%-37s", "- remove_lower_key null");
        System.out.println(" : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        System.out.printf("%-37s", "- print_ascending");
        System.out.println(" : вывести элементы коллекции в порядке возрастания");
        System.out.printf("%-37s", "- print_descending");
        System.out.println(" : вывести элементы коллекции в порядке убывания");
        System.out.printf("%-37s", "- print_field_descending_oscars_count");
        System.out.println(" : вывести значения поля oscarsCount всех элементов в порядке убывания");
        return new EmptyResponse();
    }

    /**
     * Terminates the program.
     */
    @Override
    public Response exit() {
        canExit = true;
        return new EmptyResponse();
    }

    /**
     * Displays a list of previously executed commands to the user.
     */
    @Override
    public Response history() {
        Stack<AbstractCommand> commandHistory = invoker.getCommandHistory();
        System.out.println("*command history*");
        for (AbstractCommand command : commandHistory) {
            System.out.println(command);
        }
        return new EmptyResponse();
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
                    readAndExecuteCommand(basicReader);
                } catch (InvalidCommandException | WrongNumberOfArgumentsException | WrongArgumentException |
                         InvalidScriptException | ErrorResponseException | NetworkClientException e) {
                    System.out.println(printPathStack() + ":" + lineCounter + ": " + e.getMessage());
                }
            }
            pathStack.pop();
        } catch (FileRecursionError | FileNotFoundException | FilePermissionException e) {
            System.out.println(e.getMessage());
        }
        return new EmptyResponse();
    }

    /**
     * Checks if the given path is already in the path stack.
     *
     * @param pathToCheck the path to check
     * @return true if the path is already in the path stack, false otherwise
     * @throws CustomIOException if there is an error checking the path stack
     */
    private boolean pathStackContains(String pathToCheck) throws CustomIOException {
        try {
            for (String pathFromStack : pathStack) {
                Path path1 = Paths.get(pathFromStack);
                Path path2 = Paths.get(pathToCheck);
                if (Files.isSameFile(path1, path2))
                    return true;
            }
            return false;
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    /**
     * The printPathStack() method returns a string representation of the path stack.
     *
     * @return a string representation of the path stack
     */
    private String printPathStack() {
        StringBuilder returnString = new StringBuilder();
        for (String path : pathStack) {
            returnString.append(path).append(":");
        }
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.toString();
    }

    /**
     * The inScriptMode() method checks if the program is currently executing a script.
     *
     * @return true if the program is in script mode, false otherwise
     */
    private boolean inScriptMode() {
        return !pathStack.empty();
    }
}
