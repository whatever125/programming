package server.commands;

import common.exceptions.WrongArgumentException;
import common.requests.AuthenticateUserRequest;
import common.requests.InsertRequest;
import common.requests.Request;
import common.responses.AuthenticateUserResponse;
import common.responses.InsertResponse;
import common.responses.Response;
import server.exceptions.CollectionKeyException;
import server.handlers.Executor;

import java.io.Serial;

public class AuthenticateUserCommand extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public AuthenticateUserCommand(Executor executor) {
        super("authenticate_user", executor);
    }


    @Override
    public Response execute(Request request) {
        AuthenticateUserResponse auResponse;
        try {
            AuthenticateUserRequest auRequest = (AuthenticateUserRequest) request;
            executor.authenticateUser(auRequest.login, auRequest.password);
            auResponse = new AuthenticateUserResponse(null);
        } catch (WrongArgumentException | CollectionKeyException e) {
            iResponse = new InsertResponse(e.getMessage());
        }
        return iResponse;
    }
}
