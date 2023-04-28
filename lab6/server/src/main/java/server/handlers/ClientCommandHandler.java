package server.handlers;

import common.requests.Request;
import common.responses.Response;
import server.commands.*;

import java.util.HashMap;

public class ClientCommandHandler {
    final private HashMap<String, Command> commands = new HashMap<>();
    private final Executor executor;

    public ClientCommandHandler(Executor executor) {
        this.executor = executor;
        registerCommand("info", new Info(executor));
        registerCommand("show", new Show(executor));
        registerCommand("insert", new Insert(executor));
        registerCommand("update", new Update(executor));
        registerCommand("remove_key", new RemoveKey(executor));
        registerCommand("clear", new Clear(executor));
        registerCommand("remove_greater", new RemoveGreater(executor));
        registerCommand("replace_if_lowe", new ReplaceIfLowe(executor));
        registerCommand("remove_lower_key", new RemoveLowerKey(executor));
        registerCommand("print_ascending", new PrintAscending(executor));
        registerCommand("print_descending", new PrintDescending(executor));
        registerCommand("print_field_descending_oscars_count", new PrintFieldDescendingOscarsCount(executor));
    }

    private void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public Response handle(Request request) {
        Command command = commands.get(request.name);
        Response response = command.execute(request);
        return response;
    }
}
