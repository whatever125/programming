package server.commands;

import common.requests.ReplaceIfLoweRequest;
import common.requests.Request;
import common.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class ReplaceIfLowe extends AbstractCommand {
    public ReplaceIfLowe(Executor executor) {
        super("replace_if_lowe", executor);
    }

    @Override
    public void execute(Request request) throws CollectionKeyException, WrongArgumentException {
        ReplaceIfLoweRequest rilRequest = (ReplaceIfLoweRequest) request;
        executor.replaceIfLowe(rilRequest.key, rilRequest.movieName, rilRequest.x, rilRequest.y, rilRequest.oscarsCount, rilRequest.movieGenre,
                rilRequest.mpaaRating, rilRequest.directorName, rilRequest.birthday, rilRequest.weight, rilRequest.passportID);
    }
}
