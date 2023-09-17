package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.PrintFieldDescendingOscarsCountRequest;
import common.responses.Response;

public class PrintFieldDescendingOscarsCount extends AbstractCommand {
    public PrintFieldDescendingOscarsCount(Client client, NetworkClient networkClient, String login, String password) {
        super("print_field_descending_oscars_count", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws NetworkClientException {
        PrintFieldDescendingOscarsCountRequest request = new PrintFieldDescendingOscarsCountRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
