package client.commands;

import client.Client;
import server.Executor;

public class Info extends AbstractCommandWithResult<String> {
    private String result = null;

    public Info(Client client, Executor executor) {
        super("info", client, executor);
    }

    @Override
    public void execute() {
        result = executor.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
