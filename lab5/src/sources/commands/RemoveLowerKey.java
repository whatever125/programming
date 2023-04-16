package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(Client client, Receiver receiver, Integer key) {
        super("remove_lower_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() {
        receiver.removeLowerKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
