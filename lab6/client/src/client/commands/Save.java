package client.commands;

import client.Client;
import server.Executor;

public class Save extends AbstractCommand {

    public Save(Client client, Executor executor) {
        super("save", client, executor);
    }

    @Override
    public void execute() {
        executor.save();
    }
}
