package server.commands;

import common.models.Movie;
import common.requests.Request;
import common.responses.PrintAscendingResponse;
import server.handlers.Executor;

import java.io.Serial;
import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Movie> result = null;

    public PrintAscending(Executor executor) {
        super("print_ascending", executor);
    }

    @Override
    public PrintAscendingResponse execute(Request request) {
        result = executor.printAscending();
        return new PrintAscendingResponse(null, result);
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
