package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.PrintDescendingRequest;
import common.responses.Response;

public class PrintDescending extends AbstractCommand {
    public PrintDescending(Client client, NetworkClient networkClient, String login, String password) {
        super("print_descending", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws NetworkClientException {
        PrintDescendingRequest request = new PrintDescendingRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
