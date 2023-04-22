package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.models.Movie;
import sources.server.Executor;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintFieldDescendingOscarsCount(Executor executor) {
        super("print_field_descending_oscars_count", executor);
    }

    @Override
    public void execute(Request request) {
        result = executor.printFieldDescendingOscarsCount();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
