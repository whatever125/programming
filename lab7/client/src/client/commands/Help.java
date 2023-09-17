package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.responses.Response;

public class Help extends AbstractCommand {
    public Help(Client client, NetworkClient networkClient, String login, String password) {
        super("help", client, networkClient, login, password);
    }

    @Override
    public Response execute() {
        Response response = client.help();
        return response;
    }
}
