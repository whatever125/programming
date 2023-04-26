package client.consoleClient;

import client.exceptions.CustomIOException;
import common.responses.Response;

/**
 * The Client interface represents a client application.
 */
public interface Client {
    /**
     * Displays help information for the user.
     */
    Response help();

    /**
     * Exits the client.
     */
    Response exit();

    /**
     * Displays the history of commands executed by the user.
     */
    Response history();

    /**
     * Executes a script located at the specified path.
     *
     * @param path the path to the script to execute
     * @throws CustomIOException if there is an input/output error while executing the script
     */
    Response executeScript(String path) throws CustomIOException;
}
