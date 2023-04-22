package sources.server.commands;

import sources.client.Client;
import sources.common.requests.RemoveKeyRequest;
import sources.common.requests.Request;
import sources.exceptions.receiver.CollectionKeyException;
import sources.server.Executor;

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
