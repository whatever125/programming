package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommandWithResult<List<Movie>> {
    private final List<Movie> result = null;

    public PrintFieldDescendingOscarsCount(Client client, NetworkClient networkClient) {
        super("print_field_descending_oscars_count", client, networkClient);
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
