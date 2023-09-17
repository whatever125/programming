package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.ShowRequest;
import common.responses.Response;

public class Show extends AbstractCommand {
    public Show(Client client, NetworkClient networkClient, String login, String password) {
        super("show", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws NetworkClientException {
        ShowRequest request = new ShowRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
