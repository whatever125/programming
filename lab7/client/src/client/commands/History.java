package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.responses.Response;

public class History extends AbstractCommand {
    public History(Client client, NetworkClient networkClient, String login, String password) {
        super("history", client, networkClient, login, password);
    }

    @Override
    public Response execute() {
        Response response = client.history();
        return response;
    }
}
