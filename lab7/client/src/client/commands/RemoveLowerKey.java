package client.commands;

import client.consoleClient.Client;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.RemoveLowerKeyRequest;
import common.responses.Response;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(Client client, NetworkClient networkClient, String login, String password, Integer key) {
        super("remove_lower_key", client, networkClient, login, password);
        this.key = key;
    }

    @Override
    public Response execute() throws NetworkClientException {
        RemoveLowerKeyRequest request = new RemoveLowerKeyRequest(login, password, key);
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
