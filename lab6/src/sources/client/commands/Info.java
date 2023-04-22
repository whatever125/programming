package sources.client.commands;

import sources.client.Client;
import sources.server.Executor;

public class Info extends AbstractCommandWithResult<String> {
    private String result = null;

    public Info(Client client, Executor executor) {
        super("info", client, executor);
    }

    @Override
    public void execute() {
        result = executor.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
