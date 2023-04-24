package client.commands;

import client.Client;
import server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Client client) {
        super("clear", client);
    }

    @Override
    public void execute() {
//        executor.clear();
    }
}
