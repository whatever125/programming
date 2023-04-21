package sources.receiver;

import sources.commands.AbstractCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

public class QueryHandler {
    public void handle(byte[] query) {
        try {
            String str = new String(query, StandardCharsets.UTF_8);
            System.out.println(str);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(query));
            var command = (AbstractCommand) ois.readObject();

            System.out.println(command.getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
