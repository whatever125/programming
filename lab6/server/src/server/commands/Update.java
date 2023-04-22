package server.commands;

import common.requests.Request;
import common.requests.UpdateRequest;
import server.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class Update extends AbstractCommand {
    public Update(Executor executor) {
        super("update", executor);
    }

    @Override
    public void execute(Request request) throws CollectionKeyException, WrongArgumentException {
        UpdateRequest uRequest = (UpdateRequest) request;
        executor.update(uRequest.id, uRequest.movieName, uRequest.x, uRequest.y, uRequest.oscarsCount, uRequest.movieGenre,
                uRequest.mpaaRating, uRequest.directorName, uRequest.birthday, uRequest.weight, uRequest.passportID);
    }
}
