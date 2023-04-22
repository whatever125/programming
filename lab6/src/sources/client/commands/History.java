package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class History extends AbstractCommand {
    public History(Client client, Executor executor) {
        super("history", client, executor);
    }

    @Override
    public void execute() {
        client.history();
    }
}
