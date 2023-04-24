package client.commands;

import client.consoleClient.Client;
import client.exceptions.CustomIOException;
import client.network.NetworkClient;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(Client client, NetworkClient networkClient, String path) {
        super("execute_script", client, networkClient);
        this.path = path;
    }

    @Override
    public void execute() throws CustomIOException {
        client.executeScript(path);
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
