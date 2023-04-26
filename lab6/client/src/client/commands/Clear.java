package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.requests.ClearRequest;
import common.requests.InsertRequest;
import common.requests.Request;
import common.responses.ClearResponse;
import common.responses.InsertResponse;
import common.responses.Response;

public class Clear extends AbstractCommand {
    public Clear(Client client, NetworkClient networkClient) {
        super("clear", client, networkClient);
    }

    @Override
    public ClearResponse execute() {
        ClearRequest request = new ClearRequest();
        ClearResponse response = (ClearResponse) networkClient.sendRequest(request);
        return response;
    }
}