package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class Info extends AbstractCommandWithResult<String> {
    private final String result = null;

    public Info(Client client, NetworkClient networkClient) {
        super("info", client, networkClient);
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
