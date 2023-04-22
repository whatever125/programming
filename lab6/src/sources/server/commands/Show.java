package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.models.Movie;
import sources.server.Executor;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Movie>> {
    private HashMap<Integer, Movie> result = null;

    public Show(Executor executor) {
        super("show", executor);
    }

    @Override
    public void execute(Request request) {
        result = executor.show();
    }

    @Override
    public HashMap<Integer, Movie> getResult() {
        return result;
    }
}
