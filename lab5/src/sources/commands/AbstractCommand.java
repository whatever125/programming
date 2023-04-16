package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public abstract class AbstractCommand implements Command {
    final Client client;
    final Receiver receiver;
    final String name;

    public AbstractCommand(String name, Client client, Receiver receiver) {
        this.name = name;
        this.client = client;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return name;
    }
}
