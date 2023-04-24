package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(Client client, NetworkClient networkClient, Integer key) {
        super("remove_lower_key", client, networkClient);
        this.key = key;
    }

    @Override
    public void execute() {
//        executor.removeLowerKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
