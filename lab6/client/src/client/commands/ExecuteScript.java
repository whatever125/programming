package client.commands;

import client.Client;
import client.exceptions.CustomIOException;
import server.Executor;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(Client client, String path) {
        super("execute_script", client);
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
