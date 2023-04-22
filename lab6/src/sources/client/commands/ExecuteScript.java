package sources.client.commands;

import sources.client.Client;
import sources.exceptions.io.CustomIOException;
import sources.server.Executor;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(Client client, Executor executor, String path) {
        super("execute_script", client, executor);
        this.path = path;
    }

    @Override
    public void execute() throws CustomIOException {
        client.executeScript(path);
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
