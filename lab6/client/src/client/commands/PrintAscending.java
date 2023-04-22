package client.commands;

import client.Client;
import common.models.Movie;
import server.Executor;

import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintAscending(Client client, Executor executor) {
        super("print_ascending", client, executor);
    }

    @Override
    public void execute() {
        result = executor.printAscending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
