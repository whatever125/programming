package client.commands;

import client.Client;
import server.Executor;

public class Help extends AbstractCommand {
    public Help(Client client, Executor executor) {
        super("help", client, executor);
    }

    @Override
    public void execute() {
        client.help();
    }
}
