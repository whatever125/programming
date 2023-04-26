package server.commands;

import common.models.Movie;
import common.requests.Request;
import common.responses.PrintFieldDescendingOscarsCountResponse;
import common.responses.Response;
import server.Executor;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintFieldDescendingOscarsCount(Executor executor) {
        super("print_field_descending_oscars_count", executor);
    }

    @Override
    public PrintFieldDescendingOscarsCountResponse execute(Request request) {
        result = executor.printFieldDescendingOscarsCount();
        return new PrintFieldDescendingOscarsCountResponse(null, result);
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
