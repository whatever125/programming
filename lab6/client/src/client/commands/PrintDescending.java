package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;
import common.requests.InsertRequest;
import common.requests.PrintDescendingRequest;
import common.requests.Request;
import common.responses.InsertResponse;
import common.responses.PrintAscendingResponse;
import common.responses.PrintDescendingResponse;
import common.responses.Response;

import java.util.List;
import java.util.ResourceBundle;

public class PrintDescending extends AbstractCommand {
    public PrintDescending(Client client, NetworkClient networkClient) {
        super("print_descending", client, networkClient);
    }

    @Override
    public PrintDescendingResponse execute() {
        PrintDescendingRequest request = new PrintDescendingRequest();
        PrintDescendingResponse response = (PrintDescendingResponse) networkClient.sendRequest(request);
        return response;
    }
}
