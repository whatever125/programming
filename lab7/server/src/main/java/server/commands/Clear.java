package server.commands;

import common.requests.Request;
import common.responses.ClearResponse;
import common.responses.Response;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class Clear extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public Clear(Executor executor) {
        super("clear", executor);
    }

    @Override
    public ClearResponse execute(Request request) {
        ClearResponse response;
        try {
            executor.clear(request.login);
            response = new ClearResponse(null);
        } catch (SQLException e) {
            response = new ClearResponse(e.getMessage());
        }
        return response;
    }
}
