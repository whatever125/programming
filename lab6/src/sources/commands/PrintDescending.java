package sources.commands;

import sources.client.Client;
import sources.models.Movie;
import sources.receiver.Receiver;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintDescending(Client client, Receiver receiver) {
        super("print_descending", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.printDescending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
