package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.requests.InfoRequest;
import common.requests.InsertRequest;
import common.requests.Request;
import common.responses.InfoResponse;
import common.responses.InsertResponse;
import common.responses.Response;

public class Info extends AbstractCommand {
    public Info(Client client, NetworkClient networkClient) {
        super("info", client, networkClient);
    }

    @Override
    public InfoResponse execute() {
        InfoRequest request = new InfoRequest();
        InfoResponse response = (InfoResponse) networkClient.sendRequest(request);
        return response;
    }
}
