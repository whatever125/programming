package sources.IOHandlers.receiver;

import sources.exceptions.io.FilePermissionException;
import sources.exceptions.io.InvalidFileDataException;
import sources.receiver.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileReader {
    MovieCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException;
}
