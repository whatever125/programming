package client.commands;

import client.Client;
import server.Executor;

public class History extends AbstractCommand {
    public History(Client client, Executor executor) {
        super("history", client, executor);
    }

    @Override
    public void execute() {
        client.history();
    }
}
