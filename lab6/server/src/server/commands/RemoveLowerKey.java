package server.commands;

import common.requests.RemoveLowerKeyRequest;
import common.requests.Request;
import server.Executor;

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
