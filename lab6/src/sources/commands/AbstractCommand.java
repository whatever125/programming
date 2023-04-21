package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

import java.io.Serializable;

public abstract class AbstractCommand implements Command, Serializable {
    final transient Client client;
    final transient Receiver receiver;
    final String name;

    public AbstractCommand(String name, Client client, Receiver receiver) {
        this.name = name;
        this.client = client;
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
