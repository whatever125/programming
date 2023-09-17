package server.handlers;

import common.requests.Request;
import common.responses.Response;
import common.responses.ServerErrorResponse;
import server.commands.*;
import server.network.NetworkServer;

import java.util.HashMap;

public class ServerCommandHandler implements CommandHandler {
    final private HashMap<String, Command> commands = new HashMap<>();

    public ServerCommandHandler(Executor executor, NetworkServer server) {
//        registerCommand("save", new Save(executor));
        registerCommand("exit", new Exit(executor, server));
    }

    private void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    @Override
    public Response handle(Request request) {
        Command command = commands.get(request.name);
        Response response;
        if (command == null) {
            response = new ServerErrorResponse("unknown command: " + request.name);
        } else {
            response = command.execute(request);
        }
        return response;
    }
}
