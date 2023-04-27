package server.commands;

import common.requests.InsertRequest;
import common.requests.Request;
import common.responses.InsertResponse;
import common.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;
import server.Executor;

import java.io.Serial;

public class Insert extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public Insert(Executor executor) {
        super("insert", executor);
    }

    @Override
    public InsertResponse execute(Request request) {
        InsertResponse iResponse;
        try {
            InsertRequest iRequest = (InsertRequest) request;
            executor.insert(iRequest.key, iRequest.movieName, iRequest.x, iRequest.y,
                    iRequest.oscarsCount, iRequest.movieGenre, iRequest.mpaaRating,
                    iRequest.directorName, iRequest.birthday, iRequest.weight, iRequest.passportID);
            iResponse = new InsertResponse(null);
        } catch (WrongArgumentException | CollectionKeyException e) {
            iResponse = new InsertResponse(e.getMessage());
        }
        return iResponse;
    }
}
