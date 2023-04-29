package server.commands;

import common.requests.RemoveLowerKeyRequest;
import common.requests.Request;
import common.responses.RemoveLowerKeyResponse;
import server.handlers.Executor;

import java.io.Serial;

public class RemoveLowerKey extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public RemoveLowerKey(Executor executor) {
        super("remove_lower_key", executor);
    }

    @Override
    public RemoveLowerKeyResponse execute(Request request) {
        RemoveLowerKeyRequest rlkRequest = (RemoveLowerKeyRequest) request;
        int count = executor.removeLowerKey(rlkRequest.key);
        return new RemoveLowerKeyResponse(null, count);
    }
}
