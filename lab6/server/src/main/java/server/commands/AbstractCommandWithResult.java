package server.commands;

import server.handlers.Executor;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Executor executor) {
        super(name, executor);
    }
}
