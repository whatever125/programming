package server.commands;

import common.requests.Request;
import common.responses.ClearResponse;
import common.responses.Response;
import server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Executor executor) {
        super("clear", executor);
    }

    @Override
    public ClearResponse execute(Request request) {
        executor.clear();
        return new ClearResponse(null);
    }
}
