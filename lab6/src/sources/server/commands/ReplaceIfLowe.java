package sources.server.commands;

import sources.client.Client;
import sources.common.requests.ReplaceIfLoweRequest;
import sources.common.requests.Request;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.server.Executor;

import java.time.LocalDateTime;

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
