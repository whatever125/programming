package server.commands;

import common.requests.AddUserRequest;
import common.requests.AuthenticateUserRequest;
import common.requests.Request;
import common.responses.AddUserResponse;
import common.responses.AuthenticateUserResponse;
import common.responses.Response;
import server.handlers.Executor;

import java.io.Serial;
import java.sql.SQLException;

public class AddUser extends AbstractCommand {
    @Serial
    private static final long serialVersionUID = 1L;
    public AddUser(Executor executor) {
        super("add_user", executor);
    }


    @Override
    public Response execute(Request request) {
        AddUserResponse auResponse;
        try {
            AddUserRequest auRequest = (AddUserRequest) request;
            executor.addUser(auRequest.login, auRequest.password);
            auResponse = new AddUserResponse(null);
        } catch (SQLException e) {
            auResponse = new AddUserResponse(e.getMessage());
        }
        return auResponse;
    }
}
