package server.commands;

import common.requests.RemoveLowerKeyRequest;
import common.requests.Request;
import common.responses.RemoveLowerKeyResponse;
import common.responses.ReplaceIfLoweResponse;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class RemoveLowerKey extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public RemoveLowerKey(Executor executor) {
        super("remove_lower_key", executor);
    }

    @Override
    public RemoveLowerKeyResponse execute(Request request) {
        RemoveLowerKeyRequest rlkRequest = (RemoveLowerKeyRequest) request;
        RemoveLowerKeyResponse response;
        int count = -1;
        try {
            count = executor.removeLowerKey(rlkRequest.login, rlkRequest.key);
            response = new RemoveLowerKeyResponse(null, count);
        } catch (SQLException e) {
            response = new RemoveLowerKeyResponse(e.getMessage(), count);
        }
        return response;
    }
}
