package server.commands;

import common.requests.Request;
import common.responses.ClearResponse;
import server.handlers.Executor;

import java.io.Serial;

public class Clear extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public Clear(Executor executor) {
        super("clear", executor);
    }

    @Override
    public ClearResponse execute(Request request) {
        executor.clear();
        return new ClearResponse(null);
    }
}
