package client.commands;

import client.exceptions.CustomIOException;
import common.responses.Response;

public interface Command {
    Response execute() throws CustomIOException;
}
