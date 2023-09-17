package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.InfoRequest;
import common.responses.Response;

public class Info extends AbstractCommand {
    public Info(Client client, NetworkClient networkClient, String login, String password) {
        super("info", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws NetworkClientException {
        InfoRequest request = new InfoRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
