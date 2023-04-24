package client.commands;

import client.Client;
import server.Executor;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Client client) {
        super(name, client);
    }
}
