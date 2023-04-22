package client.commands;

import client.exceptions.WrongArgumentException;
import client.exceptions.CustomIOException;
import server.exceptions.CollectionKeyException;

public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
