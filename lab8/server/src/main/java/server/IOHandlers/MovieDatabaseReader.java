package server.IOHandlers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import common.exceptions.WrongArgumentException;
import common.models.*;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import server.collection.DatabaseConnection;
import server.collection.MovieCollection;
import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.IOHandlers.InvalidDataException;
import server.exceptions.IOHandlers.SourceNotFoundException;
import server.exceptions.IOHandlers.SourcePermissionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class MovieDatabaseReader implements MovieCollectionReader {
    private final DatabaseConnection connection;
    private static final Logger logger = (Logger) LoggerFactory.getLogger("server.IOHandlers");

    public MovieDatabaseReader(DatabaseConnection connection) {
        this.connection = connection;
        logger.setLevel(Level.INFO);
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
