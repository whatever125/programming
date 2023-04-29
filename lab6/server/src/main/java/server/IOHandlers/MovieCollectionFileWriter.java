package server.IOHandlers;

import server.exceptions.CustomIOException;
import server.exceptions.FilePermissionException;
import common.models.MovieCollection;

import java.io.FileNotFoundException;

public interface MovieCollectionFileWriter {
    void write(MovieCollection movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}
