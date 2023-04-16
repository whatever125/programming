package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public class History extends AbstractCommand {
    public History(Client client, Receiver receiver) {
        super("history", client, receiver);
    }

    @Override
    public void execute() {
        client.history();
    }
}
