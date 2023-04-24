package client.commands;

import client.Client;
import common.models.Movie;
import server.Executor;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommandWithResult<List<Movie>> {
    private final List<Movie> result = null;

    public PrintFieldDescendingOscarsCount(Client client) {
        super("print_field_descending_oscars_count", client);
    }

    @Override
    public void execute() {
//        result = executor.printFieldDescendingOscarsCount();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
