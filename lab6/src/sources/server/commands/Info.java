package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.server.Executor;

public class Info extends AbstractCommandWithResult<String> {
    private String result = null;

    public Info(Executor executor) {
        super("info", executor);
    }

    @Override
    public void execute(Request request) {
        result = executor.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
