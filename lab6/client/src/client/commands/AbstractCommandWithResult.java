package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Client client, NetworkClient networkClient) {
        super(name, client, networkClient);
    }
}
