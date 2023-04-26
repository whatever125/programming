package server.commands;

import common.models.Movie;
import common.requests.Request;
import common.responses.PrintDescendingResponse;
import common.responses.Response;
import server.Executor;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintDescending(Executor executor) {
        super("print_descending", executor);
    }

    @Override
    public PrintDescendingResponse execute(Request request) {
        result = executor.printDescending();
        return new PrintDescendingResponse(null, result);
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
