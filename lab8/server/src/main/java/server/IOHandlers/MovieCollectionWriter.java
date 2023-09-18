package server.IOHandlers;

import server.exceptions.CustomIOException;
import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.IOHandlers.SourcePermissionException;
import server.collection.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionWriter {
    void write(MovieCollection movieCollection) throws IOHandlerException;
}
