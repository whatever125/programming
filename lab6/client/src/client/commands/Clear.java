package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class Clear extends AbstractCommand {
    public Clear(Client client, NetworkClient networkClient) {
        super("clear", client, networkClient);
    }

    @Override
    public void execute() {
//        executor.clear();
    }
}
