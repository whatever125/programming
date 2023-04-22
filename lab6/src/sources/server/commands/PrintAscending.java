package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.models.Movie;
import sources.server.Executor;

import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintAscending(Executor executor) {
        super("print_ascending", executor);
    }

    @Override
    public void execute(Request request) {
        result = executor.printAscending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
