package server.commands;

import common.requests.Request;
import common.requests.UpdateRequest;
import common.exceptions.WrongArgumentException;
import common.responses.UpdateResponse;
import server.exceptions.CollectionKeyException;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class Update extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public Update(Executor executor) {
        super("update", executor);
    }

    @Override
    public UpdateResponse execute(Request request) {
        UpdateRequest uRequest = (UpdateRequest) request;
        UpdateResponse response;
        try {
            executor.update(uRequest.login, uRequest.id, uRequest.movieName, uRequest.x, uRequest.y, uRequest.oscarsCount, uRequest.movieGenre,
                    uRequest.mpaaRating, uRequest.directorName, uRequest.birthday, uRequest.weight, uRequest.passportID);
            response = new UpdateResponse(null);
        } catch (WrongArgumentException | CollectionKeyException | SQLException e) {
            response = new UpdateResponse(e.getMessage());
        }
        return response;
    }
}
