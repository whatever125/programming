package server.commands;

import common.requests.Request;
import common.responses.Response;
import common.responses.SaveResponse;
import server.handlers.Executor;
import server.exceptions.CustomIOException;
import server.exceptions.FilePermissionException;

import java.io.FileNotFoundException;
import java.io.Serial;

public class Save extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;

    public Save(Executor executor) {
        super("save", executor);
    }

    @Override
    public Response execute(Request request) {
        Response response;
        try {
            executor.save();
            response = new SaveResponse(null);
        } catch (FileNotFoundException | CustomIOException | FilePermissionException e) {
            response = new SaveResponse(e.getMessage());
        }
        return response;
    }
}
