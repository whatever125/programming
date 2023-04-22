package sources.server.commands;

import sources.client.Client;
import sources.common.requests.RemoveLowerKeyRequest;
import sources.common.requests.Request;
import sources.server.Executor;

public class RemoveLowerKey extends AbstractCommand {
    public RemoveLowerKey(Executor executor) {
        super("remove_lower_key", executor);
    }

    @Override
    public void execute(Request request) {
        RemoveLowerKeyRequest rlkRequest = (RemoveLowerKeyRequest) request;
        executor.removeLowerKey(rlkRequest.key);
    }
}
