package server;

import common.exceptions.WrongArgumentException;
import common.requests.Request;
import common.responses.Response;
import server.commands.*;
import server.exceptions.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class CommandHandler {
    final private HashMap<String, Command> commands = new HashMap<>();
    private final Executor executor;

    public CommandHandler(Executor executor) throws FileNotFoundException, InvalidFileDataException, FilePermissionException {
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
        Response response = null;
        try {
            System.out.println(request.name);
            Command command = commands.get(request.name);
            if (command == null) {
                // todo throw exception
            } else {
                response = command.execute(request);
            }
        } catch (WrongArgumentException | CollectionKeyException | CustomIOException e) {
            // todo exception handling
            throw new RuntimeException(e);
        }
        return response;
    }
}
