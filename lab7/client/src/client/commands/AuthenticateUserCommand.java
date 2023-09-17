package client.commands;

import client.consoleClient.Client;
import client.exceptions.CustomIOException;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.AuthenticateUserRequest;
import common.requests.Request;
import common.responses.Response;

public class AuthenticateUserCommand extends AbstractCommand {
    public AuthenticateUserCommand(Client client, NetworkClient networkClient, String login, String password) {
        super("authenticate_user", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws CustomIOException, NetworkClientException {
        Request request = new AuthenticateUserRequest(login, password);
        Response response = networkClient.sendRequest(request);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "login='" + login + '\'' +
                '}';
    }
}
