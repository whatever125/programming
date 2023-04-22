package client.commands;

import client.Client;
import common.models.Movie;
import server.Executor;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintDescending(Client client, Executor executor) {
        super("print_descending", client, executor);
    }

    @Override
    public void execute() {
        result = executor.printDescending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
