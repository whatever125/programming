package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private final List<Movie> result = null;

    public PrintDescending(Client client, NetworkClient networkClient) {
        super("print_descending", client, networkClient);
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
