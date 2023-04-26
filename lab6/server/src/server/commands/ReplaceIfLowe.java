package server.commands;

import common.requests.ReplaceIfLoweRequest;
import common.requests.Request;
import common.exceptions.WrongArgumentException;
import common.responses.ReplaceIfLoweResponse;
import common.responses.Response;
import server.exceptions.CollectionKeyException;
import server.Executor;

public class ReplaceIfLowe extends AbstractCommand {
    public ReplaceIfLowe(Executor executor) {
        super("replace_if_lowe", executor);
    }

    @Override
    public ReplaceIfLoweResponse execute(Request request) {
        ReplaceIfLoweRequest rilRequest = (ReplaceIfLoweRequest) request;
        ReplaceIfLoweResponse response;
        try {
            boolean replaced = executor.replaceIfLowe(rilRequest.key, rilRequest.movieName, rilRequest.x, rilRequest.y, rilRequest.oscarsCount, rilRequest.movieGenre,
                    rilRequest.mpaaRating, rilRequest.directorName, rilRequest.birthday, rilRequest.weight, rilRequest.passportID);
            response = new ReplaceIfLoweResponse(null, replaced);
        } catch (WrongArgumentException | CollectionKeyException e) {
            response = new ReplaceIfLoweResponse(e.getMessage(), null);
        }
        return response;
    }
}
