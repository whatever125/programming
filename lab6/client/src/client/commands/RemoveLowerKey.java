package client.commands;

import client.Client;
import server.Executor;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(Client client, Integer key) {
        super("remove_lower_key", client);
        this.key = key;
    }

    @Override
    public void execute() {
//        executor.removeLowerKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
