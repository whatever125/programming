package sources.commands;

import sources.client.Client;
import sources.models.Movie;
import sources.receiver.Receiver;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Movie>> {
    private HashMap<Integer, Movie> result = null;

    public Show(Client client, Receiver receiver) {
        super("show", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.show();
    }

    @Override
    public HashMap<Integer, Movie> getResult() {
        return result;
    }
}
