package client;

import client.IOHandlers.*;
import client.exceptions.*;
import client.commands.*;
import common.models.*;
import common.models.helpers.MovieArgumentChecker;
import server.exceptions.CollectionKeyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static client.MovieDataConsoleReader.*;

/**
 The ConsoleClient class implements the Client interface and is responsible for
 interacting with the user through the console. It initializes the Invoker and Receiver,
 reads user input, executes commands and handles exceptions.
 */
public class ConsoleClient implements Client {
    Invoker invoker;
    private final Stack<String> pathStack = new Stack<>();
    private boolean canExit = false;

    /**
     * Main method of the ConsoleClient class. Initializes the Invoker, Receiver and Reader and
     * starts an interactive loop to read user input and execute commands.
     */
    public void main() {
        try {
            // Initialize Invoker, Receiver and Reader
            invoker = new Invoker();
            BasicReader consoleReader = new CustomConsoleReader();

            System.out.println("Data loaded successfully. You are now in interactive mode\nType 'help' to see the list of commands\n");

            while (!canExit) {
                try {
                    readAndExecuteCommand(consoleReader);
                } catch (InvalidCommandException | CollectionKeyException | WrongNumberOfArgumentsException |
                         WrongArgumentException | InvalidScriptException | CustomIOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (NullPointerException e) {
            // Handle NullPointerException thrown when LAB5 environment variable is not set
            System.out.println(e.getMessage());
            System.out.println("! path variable is null !");
            System.exit(0);
        } catch (InvalidFileDataException | EndOfInputException | FilePermissionException | FileNotFoundException e) {
            // Handle exceptions thrown when there is a problem with the data file or the user input
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Reads a command from the user and executes it using the Invoker and Receiver.
     *
     * @param basicReader the reader used to read input from the user.
     * @throws InvalidCommandException if the user enters an invalid command.
     * @throws CollectionKeyException if the user enters a command with an invalid key.
     * @throws WrongNumberOfArgumentsException if the user enters a command with the wrong number of arguments.
     * @throws WrongArgumentException if the user enters a command with invalid arguments.
     * @throws InvalidScriptException if the user enters an invalid script file.
     * @throws CustomIOException if there is an IO error when reading the user input.
     */
    private void readAndExecuteCommand(BasicReader basicReader) throws InvalidCommandException, CollectionKeyException,
            WrongNumberOfArgumentsException, WrongArgumentException, InvalidScriptException, CustomIOException {
        String input = basicReader.readLine().trim();
        if (input.startsWith("//") || input.equals("")) {
            return;
        }
        String[] inputArray = input.split("\s+");
        String commandName = inputArray[0].toLowerCase();

        String[] args = new String[inputArray.length - 1];
        System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);

        switch (commandName) {
            case "help" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Help(this, executor));
            }
            case "history" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new History(this, executor));
            }
            case "info" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                String result = invoker.executeAndReturn(new Info(this, executor));
                System.out.println(result);
            }
            case "show" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                HashMap<Integer, Movie> result = invoker.executeAndReturn(new Show(this, executor));
                PrettyPrinter.printMovieHashMap(result);
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
                    invoker.execute(new Insert(this, executor, key, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID));
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
                    invoker.execute(new Update(this, executor, id, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID));
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
                    invoker.execute(new RemoveKey(this, executor, key));
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
                invoker.execute(new Clear(this, executor));
            }
            case "save" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Save(this, executor));
            }
            case "execute_script" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                String path = args[0];
                invoker.execute(new ExecuteScript(this, executor, path));
            }
            case "exit" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Exit(this, executor));
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
                invoker.execute(new RemoveGreater(this, executor, movieName, x, y,
                        oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID));
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
                    invoker.execute(new ReplaceIfLowe(this, executor, key, movieName, x, y,
                            oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID));
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
                    invoker.execute(new RemoveLowerKey(this, executor, key));
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
                List<Movie> movieList = invoker.executeAndReturn(new PrintAscending(this, executor));
                System.out.println("*elements of collection ascended*");
                PrettyPrinter.printMovieList(movieList);
            }
            case "print_descending" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                List<Movie> movieList = invoker.executeAndReturn(new PrintDescending(this, executor));
                System.out.println("*elements of collection descended*");
                PrettyPrinter.printMovieList(movieList);
            }
            case "print_field_descending_oscars_count" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                List<Movie> movieList = invoker.executeAndReturn(new PrintFieldDescendingOscarsCount(this, executor));
                System.out.println("*oscars count descended*");
                PrettyPrinter.printMovieListOscars(movieList);
            }

            default -> throw new InvalidCommandException(commandName);
        }
    }

    /**
     * Displays a list of available commands and their descriptions to the user.
     */
    @Override
    public void help() {
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
        System.out.printf("%-37s", "- save");
        System.out.println(" : сохранить коллекцию в файл");
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
    }

    /**
     * Terminates the program.
     */
    @Override
    public void exit() {
        canExit = true;
    }

    /**
     * Displays a list of previously executed commands to the user.
     */
    @Override
    public void history() {
        Stack<AbstractCommand> commandHistory = invoker.getCommandHistory();
        System.out.println("*command history*");
        for (AbstractCommand command : commandHistory) {
            System.out.println(command);
        }
    }

    /**
     * Executes a script file containing a list of commands.
     *
     * @param path the path of the script file to execute
     * @throws CustomIOException if there is an error reading the script file
     */
    @Override
    public void executeScript(String path) throws CustomIOException {
        try {
            if (pathStackContains(path))
                throw new FileRecursionError(path);

            BasicReader basicReader = new CustomFileReader(path);
            pathStack.push(path);
            int lineCounter = 0;
            while (basicReader.hasNextLine()) {
                try {
                    lineCounter += 1;
                    readAndExecuteCommand(basicReader);
                } catch (InvalidCommandException | CollectionKeyException | WrongNumberOfArgumentsException |
                         WrongArgumentException | InvalidScriptException e) {
                    System.out.println(printPathStack() + ":" + lineCounter + ": " + e.getMessage());
                }
            }
            pathStack.pop();
        } catch (FileRecursionError | FileNotFoundException | FilePermissionException e) {
            System.out.println(e.getMessage());
        }
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
