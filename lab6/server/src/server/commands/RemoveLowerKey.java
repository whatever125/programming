package server.commands;

import common.requests.RemoveLowerKeyRequest;
import common.requests.Request;
import common.responses.RemoveKeyResponse;
import common.responses.RemoveLowerKeyResponse;
import common.responses.Response;
import server.Executor;

public class RemoveLowerKey extends AbstractCommand {
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
