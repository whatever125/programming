package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;

import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    private final List<Movie> result = null;

    public PrintAscending(Client client, NetworkClient networkClient) {
        super("print_ascending", client, networkClient);
    }

    @Override
    public void execute() {
//        result = executor.printAscending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
