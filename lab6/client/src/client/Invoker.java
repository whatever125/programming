package client;

import client.commands.AbstractCommand;
import client.commands.AbstractCommandWithResult;
import client.exceptions.*;
import common.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;

import java.util.Stack;

/**
 * The Invoker class is responsible for executing commands and maintaining a history of executed commands.
 */
public class Invoker {
    private final Stack<AbstractCommand> commandHistory = new Stack<>();

    /**
     * Executes the specified command and adds it to the command history.
     *
     * @param command the command to execute
     * @throws CollectionKeyException if there is an error with the collection key
     * @throws WrongArgumentException if there is an error with the command arguments
     * @throws CustomIOException if there is an input/output error while executing the command
     */
    public void execute(AbstractCommand command) throws CollectionKeyException, WrongArgumentException, CustomIOException {
        commandHistory.push(command);
        command.execute();
    }

    /**
     * Executes the specified command with a result and adds it to the command history.
     *
     * @param command the command to execute
     * @param <T> the type of the command result
     * @return the result of the executed command
     * @throws WrongArgumentException if there is an error with the command arguments
     * @throws CollectionKeyException if there is an error with the collection key
     * @throws CustomIOException if there is an input/output error while executing the command
     */
    public <T> T executeAndReturn(AbstractCommandWithResult<T> command) throws WrongArgumentException, CollectionKeyException, CustomIOException {
        commandHistory.push(command);
        command.execute();
        return command.getResult();
    }

    /**
     * Returns the command history maintained by the invoker.
     *
     * @return the command history stack
     */
    public Stack<AbstractCommand> getCommandHistory() {
        return commandHistory;
    }
}
