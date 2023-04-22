package client;

import client.exceptions.CustomIOException;

/**
 * The Client interface represents a client application.
 */
public interface Client {
    /**
     * Displays help information for the user.
     */
    void help();

    /**
     * Exits the client.
     */
    void exit();

    /**
     * Displays the history of commands executed by the user.
     */
    void history();

    /**
     * Executes a script located at the specified path.
     *
     * @param path the path to the script to execute
     * @throws CustomIOException if there is an input/output error while executing the script
     */
    void executeScript(String path) throws CustomIOException;
}
