package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.requests.RemoveKeyRequest;
import common.requests.RemoveLowerKeyRequest;
import common.responses.RemoveKeyResponse;
import common.responses.RemoveLowerKeyResponse;
import common.responses.Response;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(Client client, NetworkClient networkClient, Integer key) {
        super("remove_lower_key", client, networkClient);
        this.key = key;
    }

    @Override
    public RemoveLowerKeyResponse execute() {
        RemoveLowerKeyRequest request = new RemoveLowerKeyRequest(key);
        RemoveLowerKeyResponse response = (RemoveLowerKeyResponse) networkClient.sendRequest(request);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
