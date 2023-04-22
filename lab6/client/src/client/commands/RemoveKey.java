package client.commands;

import client.Client;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, Executor executor, Integer key) {
        super("remove_key", client, executor);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
        executor.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
