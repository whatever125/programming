package server.commands;

import common.models.Movie;
import common.requests.Request;
import server.Executor;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintDescending(Executor executor) {
        super("print_descending", executor);
    }

    @Override
    public void execute(Request request) {
        result = executor.printDescending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
