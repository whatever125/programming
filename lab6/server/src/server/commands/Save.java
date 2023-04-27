package server.commands;

import common.requests.Request;
import common.responses.Response;
import common.responses.SaveResponse;
import server.Executor;

import java.io.Serial;

public class Save extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;

    public Save(Executor executor) {
        super("save", executor);
    }

    @Override
    public Response execute(Request request) {
        executor.save();
        return new SaveResponse(null);
    }
}
