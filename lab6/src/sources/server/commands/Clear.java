package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Executor executor) {
        super("clear", executor);
    }

    @Override
    public void execute(Request request) {
        executor.clear();
    }
}
