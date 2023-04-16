package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public class Exit extends AbstractCommand {
    public Exit(Client client, Receiver receiver) {
        super("exit", client, receiver);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
