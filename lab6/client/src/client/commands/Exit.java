package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class Exit extends AbstractCommand {
    public Exit(Client client, NetworkClient networkClient) {
        super("exit", client, networkClient);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
