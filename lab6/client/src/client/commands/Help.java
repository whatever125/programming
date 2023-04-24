package client.commands;

import client.Client;
import server.Executor;

public class Help extends AbstractCommand {
    public Help(Client client) {
        super("help", client);
    }

    @Override
    public void execute() {
        client.help();
    }
}
