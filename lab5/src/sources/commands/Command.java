package sources.commands;

import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;

public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
