package server.commands;

import common.requests.InsertRequest;
import common.requests.Request;
import common.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;
import server.Executor;

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
