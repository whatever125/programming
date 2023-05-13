package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.ShowRequest;
import common.responses.Response;

public class Show extends AbstractCommand {
    public Show(Client client, NetworkClient networkClient) {
        super("show", client, networkClient);
    }

    @Override
    public Response execute() throws NetworkClientException {
        ShowRequest request = new ShowRequest();
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
