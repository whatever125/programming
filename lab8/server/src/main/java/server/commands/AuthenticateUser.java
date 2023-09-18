package server.commands;

import common.requests.AuthenticateUserRequest;
import common.requests.Request;
import common.responses.AuthenticateUserResponse;
import common.responses.Response;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class AuthenticateUser extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public AuthenticateUser(Executor executor) {
        super("authenticate_user", executor);
    }


    @Override
    public Response execute(Request request) {
        AuthenticateUserResponse auResponse;
        try {
            AuthenticateUserRequest auRequest = (AuthenticateUserRequest) request;
            executor.authenticateUser(auRequest.login, auRequest.password);
            auResponse = new AuthenticateUserResponse(null);
        } catch (SQLException e) {
            auResponse = new AuthenticateUserResponse(e.getMessage());
        }
        return auResponse;
    }
}
