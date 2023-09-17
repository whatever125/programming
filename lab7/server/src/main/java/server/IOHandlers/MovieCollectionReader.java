package server.IOHandlers;

import server.exceptions.FilePermissionException;
import server.exceptions.InvalidFileDataException;
import server.collection.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileReader {
    MovieCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException;
}
