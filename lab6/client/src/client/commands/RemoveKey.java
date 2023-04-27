package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.RemoveKeyRequest;
import common.responses.Response;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, NetworkClient networkClient, Integer key) {
        super("remove_key", client, networkClient);
        this.key = key;
    }

    @Override
    public Response execute() throws NetworkClientException {
        RemoveKeyRequest request = new RemoveKeyRequest(key);
        Response response = networkClient.sendRequest(request);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
