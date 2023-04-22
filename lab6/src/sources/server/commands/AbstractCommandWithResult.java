package sources.server.commands;

import sources.client.Client;
import sources.server.Executor;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Executor executor) {
        super(name, executor);
    }
}
