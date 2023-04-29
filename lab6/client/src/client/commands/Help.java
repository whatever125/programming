package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.responses.Response;

public class Help extends AbstractCommand {
    public Help(Client client, NetworkClient networkClient) {
        super("help", client, networkClient);
    }

    @Override
    public Response execute() {
        Response response = client.help();
        return response;
    }
}
