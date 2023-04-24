package client.commands;

import client.Client;
import common.models.Movie;
import server.Executor;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private final List<Movie> result = null;

    public PrintDescending(Client client) {
        super("print_descending", client);
    }

    @Override
    public void execute() {
//        result = executor.printDescending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
