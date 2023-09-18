package server.commands;

import common.models.Movie;
import common.requests.Request;
import common.responses.PrintDescendingResponse;
import server.handlers.Executor;

import java.io.Serial;
import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    @Serial
    private static final long serialVersionUID = 1L;
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
