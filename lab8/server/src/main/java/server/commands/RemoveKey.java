package server.commands;

import common.requests.RemoveKeyRequest;
import common.requests.Request;
import common.responses.RemoveKeyResponse;
import server.exceptions.CollectionKeyException;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class RemoveKey extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public RemoveKey(Executor executor) {
        super("remove_key", executor);
    }

    @Override
    public RemoveKeyResponse execute(Request request) {
        RemoveKeyResponse response;
        try {
            RemoveKeyRequest rkRequeest = (RemoveKeyRequest) request;
            executor.removeKey(rkRequeest.login, rkRequeest.key);
            response = new RemoveKeyResponse(null);
        } catch (CollectionKeyException | SQLException e) {
            response = new RemoveKeyResponse(e.getMessage());
        }
        return response;
    }
}
