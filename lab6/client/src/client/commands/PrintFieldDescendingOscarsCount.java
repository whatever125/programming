package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;
import common.requests.InsertRequest;
import common.requests.PrintFieldDescendingOscarsCountRequest;
import common.requests.Request;
import common.responses.InsertResponse;
import common.responses.PrintFieldDescendingOscarsCountResponse;
import common.responses.Response;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommand {
    public PrintFieldDescendingOscarsCount(Client client, NetworkClient networkClient) {
        super("print_field_descending_oscars_count", client, networkClient);
    }

    @Override
    public PrintFieldDescendingOscarsCountResponse execute() {
        PrintFieldDescendingOscarsCountRequest request = new PrintFieldDescendingOscarsCountRequest();
        PrintFieldDescendingOscarsCountResponse response = (PrintFieldDescendingOscarsCountResponse) networkClient.sendRequest(request);
        return response;
    }
}
