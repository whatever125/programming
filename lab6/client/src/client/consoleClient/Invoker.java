package client.consoleClient;

import client.commands.AbstractCommand;
import client.exceptions.*;
import common.responses.Response;

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
     * @throws CustomIOException if there is an input/output error while executing the command
     */
    public Response execute(AbstractCommand command) throws CustomIOException, NetworkClientException {
        commandHistory.push(command);
        Response response = command.execute();
        return response;
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
