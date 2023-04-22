package sources.server;

import sources.common.requests.Request;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.server.commands.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class QueryHandler {
    final private HashMap<String, Command> commands = new HashMap<>();
    private final Executor executor;

    public QueryHandler(Executor executor) {
        this.executor = executor;
        registerCommand("info", new Info(executor));
        registerCommand("show", new Show(executor));
        registerCommand("insert", new Insert(executor));
        registerCommand("update", new Update(executor));
        registerCommand("remove_key", new RemoveKey(executor));
        registerCommand("clear", new Clear(executor));
        registerCommand("save", new Save(executor));
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

    public void handle(byte[] query) {
        try {
            String str = new String(query, StandardCharsets.UTF_8);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(query));
            var request = (Request) ois.readObject();

            System.out.println(request.getName());
            Command command = commands.get(request.getName());
            if (command == null) {
                // todo throw exception
            } else {
                command.execute(request);
            }
        } catch (IOException | ClassNotFoundException | WrongArgumentException | CollectionKeyException |
                 CustomIOException e) {
            // todo exception handling
            throw new RuntimeException(e);
        }
    }
}
