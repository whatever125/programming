package client.commands;

import client.Client;
import common.models.Movie;
import server.Executor;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Movie>> {
    private final HashMap<Integer, Movie> result = null;

    public Show(Client client) {
        super("show", client);
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
