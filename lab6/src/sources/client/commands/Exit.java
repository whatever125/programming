package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class Exit extends AbstractCommand {
    public Exit(Client client, Executor executor) {
        super("exit", client, executor);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
