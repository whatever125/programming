package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class Save extends AbstractCommand {

    public Save(Client client, Executor executor) {
        super("save", client, executor);
    }

    @Override
    public void execute() {
        executor.save();
    }
}
