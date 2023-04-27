package client.commands;

import client.exceptions.CustomIOException;
import client.exceptions.NetworkClientException;
import common.responses.Response;

public interface Command {
    Response execute() throws CustomIOException, NetworkClientException;
}
