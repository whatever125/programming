package server.commands;

import common.requests.Request;
import common.responses.Response;
import server.handlers.Executor;
import server.network.NetworkServer;

import java.io.Serial;

public class Exit extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    private final transient NetworkServer server;

    public Exit(Executor executor, NetworkServer server) {
        super("exit", executor);
        this.server = server;
    }

    @Override
    public Response execute(Request request) {
        Response response = server.exit();
        return response;
    }
}
