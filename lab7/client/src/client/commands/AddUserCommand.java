package client.commands;

import client.consoleClient.Client;
import client.exceptions.CustomIOException;
import client.exceptions.NetworkClientException;
import client.network.NetworkClient;
import common.requests.AddUserRequest;
import common.requests.Request;
import common.responses.Response;

public class AddUserCommand extends AbstractCommand {
    public AddUserCommand(Client client, NetworkClient networkClient, String login, String password) {
        super("add_user", client, networkClient, login, password);
    }

    @Override
    public Response execute() throws CustomIOException, NetworkClientException {
        Request request = new AddUserRequest(login, password);
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
