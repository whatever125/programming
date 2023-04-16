package sources.commands;

import sources.client.Client;
import sources.models.Movie;
import sources.receiver.Receiver;

import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintAscending(Client client, Receiver receiver) {
        super("print_ascending", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.printAscending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
