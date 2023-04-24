package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class Help extends AbstractCommand {
    public Help(Client client, NetworkClient networkClient) {
        super("help", client, networkClient);
    }

    @Override
    public void execute() {
        client.help();
    }
}
