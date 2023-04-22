package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class Help extends AbstractCommand {
    public Help(Client client, Executor executor) {
        super("help", client, executor);
    }

    @Override
    public void execute() {
        client.help();
    }
}
