package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import server.exceptions.CollectionKeyException;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, NetworkClient networkClient, Integer key) {
        super("remove_key", client, networkClient);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
//        executor.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
