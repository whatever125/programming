package server.commands;

import common.requests.Request;
import server.exceptions.CustomIOException;
import server.exceptions.WrongArgumentException;
import server.exceptions.CollectionKeyException;

public interface Command {
    void execute(Request request) throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
