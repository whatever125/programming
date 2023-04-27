package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.responses.Response;

public class Exit extends AbstractCommand {
    public Exit(Client client, NetworkClient networkClient) {
        super("exit", client, networkClient);
    }

    @Override
    public Response execute() {
        Response response = client.exit();
        return response;
    }
}
