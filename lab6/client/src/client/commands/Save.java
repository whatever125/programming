package client.commands;

import client.Client;
import server.Executor;

public class Save extends AbstractCommand {

    public Save(Client client) {
        super("save", client);
    }

    @Override
    public void execute() {
//        executor.save();
    }
}
