package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Client client, Executor executor) {
        super(name, client, executor);
    }
}
