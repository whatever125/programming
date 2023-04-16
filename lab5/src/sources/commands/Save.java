package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public class Save extends AbstractCommand {

    public Save(Client client, Receiver receiver) {
        super("save", client, receiver);
    }

    @Override
    public void execute() {
        receiver.save();
    }
}
