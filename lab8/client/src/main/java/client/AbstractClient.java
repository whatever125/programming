package client;

import client.commands.*;
import client.consoleClient.PrettyPrinter;
import client.exceptions.*;
import client.network.NetworkClient;
import client.network.TCPClient;
import common.IOHandlers.BasicReader;
import common.IOHandlers.ScriptReader;
import common.exceptions.FilePermissionException;
import common.exceptions.InvalidCommandException;
import common.exceptions.WrongArgumentException;
import common.exceptions.WrongNumberOfArgumentsException;
import common.models.Movie;
import common.models.MovieGenre;
import common.models.MpaaRating;
import common.models.helpers.MovieArgumentChecker;
import common.responses.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static client.consoleClient.MovieDataConsoleReader.*;
import static client.consoleClient.MovieDataConsoleReader.readPassportID;

public abstract class AbstractClient implements Client {
    protected static final String HOST = "localhost";
    protected static final int PORT = 3683;

    protected final NetworkClient networkClient;
    protected Invoker invoker = new Invoker();
    protected final Stack<String> pathStack = new Stack<>();
    protected boolean canExit = false;

    protected String login;
    protected String password;

    public AbstractClient() {
        this.networkClient = new TCPClient(HOST, PORT);
    }

    public abstract void run();

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
     * Displays a list of available commands and their descriptions to the user.
     */
    @Override
    public Response help() {
        String stringBuilder = "*list of commands*\n" +
                String.format("%-37s", "- help") +
                " : вывести справку по доступным командам\n" +
                String.format("%-37s", "- info") +
                " : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                String.format("%-37s", "- show") +
                " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                String.format("%-37s", "- insert null {element}") +
                " : добавить новый элемент с заданным ключом\n" +
                String.format("%-37s", "- update id {element}") +
                " : обновить значение элемента коллекции, id которого равен заданному\n" +
                String.format("%-37s", "- remove_key null") +
                " : удалить элемент из коллекции по его ключу\n" +
                String.format("%-37s", "- clear") +
                " : очистить коллекцию\n" +
                String.format("%-37s", "- execute_script file_name") +
                " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                String.format("%-37s", "- exit") +
                " : завершить программу (без сохранения в файл)\n" +
                String.format("%-37s", "- remove_greater {element}") +
                " : удалить из коллекции все элементы, превышающие заданный\n" +
                String.format("%-37s", "- replace_if_lowe null {element}") +
                " : заменить значение по ключу, если новое значение меньше старого\n" +
                String.format("%-37s", "- remove_lower_key null") +
                " : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                String.format("%-37s", "- print_ascending") +
                " : вывести элементы коллекции в порядке возрастания\n" +
                String.format("%-37s", "- print_descending") +
                " : вывести элементы коллекции в порядке убывания\n" +
                String.format("%-37s", "- print_field_descending_oscars_count") +
                " : вывести значения поля oscarsCount всех элементов в порядке убывания\n";
        return new HelpResponse(null, stringBuilder);
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
    protected String readAndExecuteCommand(BasicReader basicReader) throws InvalidCommandException,
            WrongNumberOfArgumentsException, WrongArgumentException, InvalidScriptException, CustomIOException,
            ErrorResponseException, NetworkClientException {
        String input = basicReader.readLine().trim();
        if (input.startsWith("//") || input.equals("")) {
            return null;
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
                HelpResponse helpResponse = (HelpResponse) abstractResponse;
                if (helpResponse.error == null) {
                    return helpResponse.help;
                } else {
                    return helpResponse.error;
                }
            }
            case "info" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Info(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                InfoResponse response = (InfoResponse) abstractResponse;
                if (response.error == null) {
                    return response.info;
                } else {
                    return response.error;
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
                    return PrettyPrinter.printMovieHashMap(movieHashMap);
                } else {
                    return response.error;
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
                        return "*element added successfully*";
                    } else {
                        return response.error;
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "not an integer";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        return errorMessage;
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
                        return "*element updated successfully*";
                    } else {
                        return response.error;
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "not an integer";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        return errorMessage;
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
                        return "*element removed successfully*";
                    } else {
                        return response.error;
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "not an integer";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        return errorMessage;
                    }
                }
            }
            case "clear" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();

                AbstractCommand command = new Clear(this, networkClient, login, password);
                Response abstractResponse = invoker.execute(command);
                handleErrorResponse(abstractResponse);
                return "*movies created by '" + login + "' cleared successfully*";
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
                        return "*no elements removed*";
                    } else {
                        return "* " + response.count + " elements removed successfully*";
                    }
                } else {
                    return response.error;
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
                            return "*element replaced successfully*";
                        } else {
                            return "*element was not replaced*";
                        }
                    } else {
                        return response.error;
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "not an integer";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        return errorMessage;
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
                            return "*no elements removed*";
                        } else {
                            return "* " + response.count + " elements removed successfully*";
                        }
                    } else {
                        return response.error;
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = "not an integer";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        return errorMessage;
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
                    List<Movie> movieList = response.movieList;
                    return "*elements of collection ascended*\n" + PrettyPrinter.printMovieList(movieList);
                } else {
                    return response.error;
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
                    List<Movie> movieList = response.movieList;
                    return "*elements of collection descended*\n" + PrettyPrinter.printMovieList(movieList);
                } else {
                    return response.error;
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
                    List<Movie> movieList = response.movieList;
                    return "*oscars count descended*\n" + PrettyPrinter.printMovieListOscars(movieList);
                } else {
                    return response.error;
                }
            }

            default -> throw new InvalidCommandException(commandName);
        }
        return null;
    }

    public void handleErrorResponse(Response response) throws ErrorResponseException {
        if (response != null && Objects.equals(response.name, "error")) {
            throw new ErrorResponseException(response.error);
        }
    }

    /**
     * Checks if the given path is already in the path stack.
     *
     * @param pathToCheck the path to check
     * @return true if the path is already in the path stack, false otherwise
     * @throws CustomIOException if there is an error checking the path stack
     */
    protected boolean pathStackContains(String pathToCheck) throws CustomIOException {
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
     * The inScriptMode() method checks if the program is currently executing a script.
     *
     * @return true if the program is in script mode, false otherwise
     */
    protected boolean inScriptMode() {
        return !pathStack.empty();
    }

    /**
     * The printPathStack() method returns a string representation of the path stack.
     *
     * @return a string representation of the path stack
     */
    protected String printPathStack() {
        StringBuilder returnString = new StringBuilder();
        for (String path : pathStack) {
            returnString.append(path).append(":");
        }
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.toString();
    }

    public NetworkClient getNetworkClient() {
        return networkClient;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
