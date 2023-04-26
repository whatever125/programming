package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.requests.RemoveGreaterRequest;
import common.requests.RemoveKeyRequest;
import common.requests.Request;
import common.responses.RemoveGreaterResponse;
import common.responses.RemoveKeyResponse;
import common.responses.Response;
import server.exceptions.CollectionKeyException;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, NetworkClient networkClient, Integer key) {
        super("remove_key", client, networkClient);
        this.key = key;
    }

    @Override
    public RemoveKeyResponse execute() {
        RemoveKeyRequest request = new RemoveKeyRequest(key);
        RemoveKeyResponse response = (RemoveKeyResponse) networkClient.sendRequest(request);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
