package server.IOHandlers;

import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileReader {
    MovieCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException;
}
