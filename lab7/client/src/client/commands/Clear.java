package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.ClearRequest;
import common.responses.Response;

public class Clear extends AbstractCommand {
    public Clear(Client client, NetworkClient networkClient, String login, String password) {
        super("clear", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws NetworkClientException {
        ClearRequest request = new ClearRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }
}