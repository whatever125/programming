package server.commands;

import common.requests.RemoveKeyRequest;
import common.requests.Request;
import common.responses.RemoveKeyResponse;
import common.responses.Response;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class RemoveKey extends AbstractCommand {
    public RemoveKey(Executor executor) {
        super("remove_key", executor);
    }

    @Override
    public RemoveKeyResponse execute(Request request) {
        RemoveKeyResponse response;
        try {
            RemoveKeyRequest rkRequeest = (RemoveKeyRequest) request;
            executor.removeKey(rkRequeest.key);
            response = new RemoveKeyResponse(null);
        } catch (CollectionKeyException e) {
            response = new RemoveKeyResponse(e.getMessage());
        }
        return response;
    }
}
