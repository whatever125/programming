package client.commands;

import client.Client;
import server.Executor;

public class Exit extends AbstractCommand {
    public Exit(Client client) {
        super("exit", client);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
