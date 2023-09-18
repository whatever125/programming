package server.IOHandlers;

import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.IOHandlers.SourcePermissionException;
import server.exceptions.IOHandlers.InvalidDataException;
import server.collection.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionReader {
    MovieCollection read() throws IOHandlerException;
}
