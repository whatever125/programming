package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;

public class Save extends AbstractCommand {

    public Save(Client client, NetworkClient networkClient) {
        super("save", client, networkClient);
    }

    @Override
    public void execute() {
//        executor.save();
    }
}
