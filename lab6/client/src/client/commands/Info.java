package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.InfoRequest;
import common.responses.Response;

public class Info extends AbstractCommand {
    public Info(Client client, NetworkClient networkClient) {
        super("info", client, networkClient);
    }

    @Override
    public Response execute() throws NetworkClientException {
        InfoRequest request = new InfoRequest();
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
