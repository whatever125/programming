package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.server.Executor;

public class Save extends AbstractCommand {

    public Save(Executor executor) {
        super("save", executor);
    }

    @Override
    public void execute(Request request) {
        executor.save();
    }
}
