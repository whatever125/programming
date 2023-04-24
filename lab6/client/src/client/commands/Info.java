package client.commands;

import client.Client;
import server.Executor;

public class Info extends AbstractCommandWithResult<String> {
    private final String result = null;

    public Info(Client client) {
        super("info", client);
    }

    @Override
    public void execute() {
//        result = executor.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
