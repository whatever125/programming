package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;
import common.requests.InsertRequest;
import common.requests.Request;
import common.requests.ShowRequest;
import common.responses.InsertResponse;
import common.responses.Response;
import common.responses.ShowResponse;

import java.util.HashMap;

public class Show extends AbstractCommand {
    public Show(Client client, NetworkClient networkClient) {
        super("show", client, networkClient);
    }

    @Override
    public ShowResponse execute() {
        ShowRequest request = new ShowRequest();
        ShowResponse response = (ShowResponse) networkClient.sendRequest(request);
        return response;
    }
}
