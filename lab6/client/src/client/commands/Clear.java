package client.commands;

import client.Client;
import server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Client client, Executor executor) {
        super("clear", client, executor);
    }

    @Override
    public void execute() {
        executor.clear();
    }
}
