package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.models.Movie;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Movie>> {
    private final HashMap<Integer, Movie> result = null;

    public Show(Client client, NetworkClient networkClient) {
        super("show", client, networkClient);
    }

    @Override
    public void execute() {
//        result = executor.show();
    }

    @Override
    public HashMap<Integer, Movie> getResult() {
        return result;
    }
}
