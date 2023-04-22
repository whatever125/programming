package sources.server.commands;

import sources.client.Client;
import sources.common.requests.Request;
import sources.common.requests.UpdateRequest;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.server.Executor;

import java.time.LocalDateTime;

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
