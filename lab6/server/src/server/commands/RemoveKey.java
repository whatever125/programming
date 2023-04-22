package server.commands;

import common.requests.RemoveKeyRequest;
import common.requests.Request;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class RemoveKey extends AbstractCommand {
    public RemoveKey(Executor executor) {
        super("remove_key", executor);
    }

    @Override
    public void execute(Request request) throws CollectionKeyException {
        RemoveKeyRequest rkRequeest = (RemoveKeyRequest) request;
        executor.removeKey(rkRequeest.key);
    }
}
