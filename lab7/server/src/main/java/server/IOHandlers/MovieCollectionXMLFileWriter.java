package server.IOHandlers;

import server.exceptions.CustomIOException;
import server.exceptions.IOHandlers.IOHandlerException;
import server.exceptions.IOHandlers.SourceNotFoundException;
import server.exceptions.IOHandlers.SourcePermissionException;
import common.models.Movie;
import server.collection.MovieCollection;

import java.io.*;
import java.util.HashMap;

public class MovieCollectionXMLFileWriter implements MovieCollectionWriter {
    private final String path;

    public MovieCollectionXMLFileWriter(String path) {
        this.path = path;
    }

    @Override
    public void write(MovieCollection movieCollection) throws IOHandlerException {
        checkFile();
        HashMap<Integer, Movie> movieHashMap = movieCollection.getMovieHashMap();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bufferedWriter.write("<movieCollection collectionCreationDate=\"" + movieCollection.getCreationDate() + "\">\n");
            for (Movie movie : movieHashMap.values()) {
                String indent = "    ";
                bufferedWriter.write(indent.repeat(1) + "<movie>\n");
                bufferedWriter.write(indent.repeat(2) + "<id>" + movie.getID() + "</id>\n");
                bufferedWriter.write(indent.repeat(2) + "<movieName>" + movie.getName() + "</movieName>\n");
                bufferedWriter.write(indent.repeat(2) + "<coordinates>\n");
                bufferedWriter.write(indent.repeat(3) + "<x>" + movie.getCoordinates().getX() + "</x>\n");
                bufferedWriter.write(indent.repeat(3) + "<y>" + movie.getCoordinates().getY() + "</y>\n");
                bufferedWriter.write(indent.repeat(2) + "</coordinates>\n");
                bufferedWriter.write(indent.repeat(2) + "<movieCreationDate>" + movie.getCreationDate() + "</movieCreationDate>\n");
                bufferedWriter.write(indent.repeat(2) + "<oscarsCount>" + movie.getOscarsCount() + "</oscarsCount>\n");
                bufferedWriter.write(indent.repeat(2) + "<genre>" + movie.getGenre() + "</genre>\n");
                bufferedWriter.write(indent.repeat(2) + "<mpaaRating>" + movie.getMpaaRating() + "</mpaaRating>\n");
                bufferedWriter.write(indent.repeat(2) + "<director>\n");
                bufferedWriter.write(indent.repeat(3) + "<directorName>" + movie.getDirector().getName() + "</directorName>\n");
                bufferedWriter.write(indent.repeat(3) + "<birthday>" + movie.getDirector().getBirthday() + "</birthday>\n");
                bufferedWriter.write(indent.repeat(3) + "<weight>" + movie.getDirector().getWeight() + "</weight>\n");
                if (movie.getDirector().getPassportID() == null) {
                    bufferedWriter.write(indent.repeat(3) + "<passportID/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(3) + "<passportID>" + movie.getDirector().getPassportID() + "</passportID>\n");
                }
                bufferedWriter.write(indent.repeat(2) + "</director>\n");
                bufferedWriter.write(indent.repeat(1) + "</movie>\n");
            }
            bufferedWriter.write("</movieCollection>");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new IOHandlerException("! " + this.getClass().getName() + ": IOException when writing to " + path + " !");
        }
    }

    private void checkFile() throws IOHandlerException {
        File file = new File(path);
        if (!file.exists())
            throw new SourceNotFoundException("! file " + path + " not found !");
        if (!file.canWrite())
            throw new SourcePermissionException("! no write permission for file " + path + "  !");
    }
}
