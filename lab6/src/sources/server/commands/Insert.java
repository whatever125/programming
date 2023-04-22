package sources.server.commands;

import sources.client.Client;
import sources.common.requests.InsertRequest;
import sources.common.requests.Request;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.server.Executor;

public class Insert extends AbstractCommand {
    public Insert(Executor executor) {
        super("insert", executor);
    }

    @Override
    public void execute(Request request) throws CollectionKeyException, WrongArgumentException {
        InsertRequest iRequest = (InsertRequest) request;
        executor.insert(iRequest.key, iRequest.movieName, iRequest.x, iRequest.y,
                iRequest.oscarsCount, iRequest.movieGenre, iRequest.mpaaRating,
                iRequest.directorName, iRequest.birthday, iRequest.weight, iRequest.passportID);
    }
}
