package client.commands;

import client.consoleClient.Client;
import client.exceptions.CustomIOException;
import client.network.NetworkClient;
import common.responses.Response;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(Client client, NetworkClient networkClient, String login, String password, String path) {
        super("execute_script", client, networkClient, login, password);
        this.path = path;
    }

    @Override
    public Response execute() throws CustomIOException {
        Response response = client.executeScript(path);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
