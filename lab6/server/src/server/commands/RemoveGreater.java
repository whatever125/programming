package server.commands;

import common.requests.RemoveGreaterRequest;
import common.requests.Request;
import common.exceptions.WrongArgumentException;
import common.responses.RemoveGreaterResponse;
import common.responses.Response;
import server.Executor;

public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(Executor executor) {
        super("remove_greater", executor);
    }

    @Override
    public RemoveGreaterResponse execute(Request request) throws WrongArgumentException {
        RemoveGreaterRequest rgRequest = (RemoveGreaterRequest) request;
        int count = executor.removeGreater(rgRequest.movieName, rgRequest.x, rgRequest.y, rgRequest.oscarsCount, rgRequest.movieGenre,
                rgRequest.mpaaRating, rgRequest.directorName, rgRequest.birthday, rgRequest.weight, rgRequest.passportID);
        return new RemoveGreaterResponse(null, count);
    }
}
