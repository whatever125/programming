package server.commands;

import common.models.Movie;
import common.requests.Request;
import common.responses.ShowResponse;
import server.Executor;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Movie>> {
    private HashMap<Integer, Movie> result = null;

    public Show(Executor executor) {
        super("show", executor);
    }

    @Override
    public ShowResponse execute(Request request) {
        result = executor.show();
        ShowResponse showResponse = new ShowResponse(null, result);
        return showResponse;
    }

    @Override
    public HashMap<Integer, Movie> getResult() {
        return result;
    }
}
