package sources.server.commands;

import sources.client.Client;
import sources.common.requests.InsertRequest;
import sources.common.requests.RemoveGreaterRequest;
import sources.common.requests.Request;
import sources.exceptions.io.WrongArgumentException;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.server.Executor;

import java.time.LocalDateTime;

public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(Executor executor) {
        super("remove_greater", executor);
    }

    @Override
    public void execute(Request request) throws WrongArgumentException {
        RemoveGreaterRequest rgRequest = (RemoveGreaterRequest) request;
        executor.removeGreater(rgRequest.movieName, rgRequest.x, rgRequest.y, rgRequest.oscarsCount, rgRequest.movieGenre,
                rgRequest.mpaaRating, rgRequest.directorName, rgRequest.birthday, rgRequest.weight, rgRequest.passportID);
    }
}
