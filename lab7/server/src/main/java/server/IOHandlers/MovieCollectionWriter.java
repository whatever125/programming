package server.IOHandlers;

import server.exceptions.CustomIOException;
import server.exceptions.FilePermissionException;
import server.collection.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileWriter {
    void write(MovieCollection movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}