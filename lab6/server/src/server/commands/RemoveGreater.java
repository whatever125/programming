package server.commands;

import common.requests.RemoveGreaterRequest;
import common.requests.Request;
import common.exceptions.WrongArgumentException;
import common.responses.RemoveGreaterResponse;
import server.Executor;

import java.io.Serial;

public class RemoveGreater extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public RemoveGreater(Executor executor) {
        super("remove_greater", executor);
    }

    @Override
    public RemoveGreaterResponse execute(Request request) {
        RemoveGreaterResponse rgResponse;
        try {
            RemoveGreaterRequest rgRequest = (RemoveGreaterRequest) request;
            int count = executor.removeGreater(rgRequest.movieName, rgRequest.x, rgRequest.y, rgRequest.oscarsCount, rgRequest.movieGenre,
                    rgRequest.mpaaRating, rgRequest.directorName, rgRequest.birthday, rgRequest.weight, rgRequest.passportID);
            rgResponse = new RemoveGreaterResponse(null, count);
        } catch (WrongArgumentException e) {
            rgResponse = new RemoveGreaterResponse(e.getMessage(), null);
        }
        return rgResponse;
    }
}
