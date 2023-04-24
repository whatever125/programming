package client.commands;

import client.Client;
import server.Executor;

public class History extends AbstractCommand {
    public History(Client client) {
        super("history", client);
    }

    @Override
    public void execute() {
        client.history();
    }
}
