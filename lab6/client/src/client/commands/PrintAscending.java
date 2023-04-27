package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.PrintAscendingRequest;
import common.responses.Response;

public class PrintAscending extends AbstractCommand {
    public PrintAscending(Client client, NetworkClient networkClient) {
        super("print_ascending", client, networkClient);
    }

    @Override
    public Response execute() throws NetworkClientException {
        PrintAscendingRequest request = new PrintAscendingRequest();
        Response response = networkClient.sendRequest(request);
        return response;
    }
}
