package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Client client, Executor executor) {
        super("clear", client, executor);
    }

    @Override
    public void execute() {
        executor.clear();
    }
}
