package sources.commands;

import sources.client.Client;
import sources.receiver.Receiver;

public class Help extends AbstractCommand {
    public Help(Client client, Receiver receiver) {
        super("help", client, receiver);
    }

    @Override
    public void execute() {
        client.help();
    }
}
