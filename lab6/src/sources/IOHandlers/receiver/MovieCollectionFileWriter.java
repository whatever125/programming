package sources.IOHandlers.receiver;

import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.FilePermissionException;
import sources.server.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileWriter {
    void write(MovieCollection movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}
