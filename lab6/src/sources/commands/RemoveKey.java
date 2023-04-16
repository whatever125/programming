package sources.commands;

import sources.client.Client;
import sources.exceptions.receiver.CollectionKeyException;
import sources.receiver.Receiver;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, Receiver receiver, Integer key) {
        super("remove_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
        receiver.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
