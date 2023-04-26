package server.commands;

import common.requests.Request;
import common.responses.Response;
import server.Executor;

public class Save extends AbstractCommand {

    public Save(Executor executor) {
        super("save", executor);
    }

    @Override
    public Response execute(Request request) {
        executor.save();
        return null;
    }
}
