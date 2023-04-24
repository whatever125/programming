package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class History extends AbstractCommand {
    public History(Client client, NetworkClient networkClient) {
        super("history", client, networkClient);
    }

    @Override
    public void execute() {
        client.history();
    }
}
