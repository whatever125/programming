package server.IOHandlers;

import common.models.*;
import server.collection.DatabaseConnection;
import server.collection.MovieCollection;
import server.exceptions.IOHandlers.IOHandlerException;

import java.sql.SQLException;
import java.util.HashMap;

public class MovieDatabaseReader implements MovieCollectionReader {
    private final DatabaseConnection connection;

    public MovieDatabaseReader(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public MovieCollection read() throws IOHandlerException {
        MovieCollection movieCollection = new MovieCollection();

        try {
            HashMap<Integer, Movie> movies = connection.getAllMovies();
            movieCollection.setMovieHashMap(movies);
        } catch (SQLException e) {
            throw new IOHandlerException(e.getMessage());
        }

        return movieCollection;
    }
}
