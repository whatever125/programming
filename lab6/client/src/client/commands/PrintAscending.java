package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;
import common.requests.ClearRequest;
import common.requests.PrintAscendingRequest;
import common.requests.Request;
import common.responses.ClearResponse;
import common.responses.PrintAscendingResponse;
import common.responses.Response;

import java.util.List;

public class PrintAscending extends AbstractCommand {
    public PrintAscending(Client client, NetworkClient networkClient) {
        super("print_ascending", client, networkClient);
    }

    @Override
    public PrintAscendingResponse execute() {
        PrintAscendingRequest request = new PrintAscendingRequest();
        PrintAscendingResponse response = (PrintAscendingResponse) networkClient.sendRequest(request);
        return response;
    }
}
