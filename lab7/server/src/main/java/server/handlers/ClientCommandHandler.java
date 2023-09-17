package server.handlers;

import common.requests.Request;
import common.responses.Response;
import common.responses.ServerErrorResponse;
import server.collection.DatabaseConnection;
import server.commands.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientCommandHandler implements CommandHandler {
    final private HashMap<String, Command> commands = new HashMap<>();
    final private ArrayList<Command> startCommands = new ArrayList<>();
    final private DatabaseConnection databaseConnection;
    private final Executor executor;

    public ClientCommandHandler(Executor executor, DatabaseConnection databaseConnection) {
        this.executor = executor;
        this.databaseConnection = databaseConnection;
        registerCommands();
    }

    private void registerCommands() {
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
        registerStartCommand("authenticate_user", new AuthenticateUser(executor));
        registerStartCommand("add_user", new AddUser(executor));
    }

    private void registerStartCommand(String name, Command command) {
        registerCommand(name, command);
        startCommands.add(command);
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
            try {
                if (!startCommands.contains(command)) {
                    databaseConnection.authenticateUser(request.login, request.password);
                }
                response = command.execute(request);
            } catch (SQLException e) {
                response = new ServerErrorResponse("unable to authenticate user: " + e.getMessage());
            }
        }
        return response;
    }
}
