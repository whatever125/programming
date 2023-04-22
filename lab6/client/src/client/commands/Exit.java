package client.commands;

import client.Client;
import server.Executor;

public class Exit extends AbstractCommand {
    public Exit(Client client, Executor executor) {
        super("exit", client, executor);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
