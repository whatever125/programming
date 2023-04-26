package server.commands;

import common.requests.Request;
import common.responses.Response;
import server.exceptions.CustomIOException;
import common.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;

public interface Command {
    Response execute(Request request) throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
