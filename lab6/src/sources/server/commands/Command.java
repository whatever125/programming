package sources.server.commands;

import sources.common.requests.Request;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;

public interface Command {
    void execute(Request request) throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
