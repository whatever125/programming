package sources.IOHandlers.receiver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import sources.exceptions.io.FilePermissionException;
import sources.exceptions.io.InvalidFileDataException;
import sources.exceptions.io.WrongArgumentException;
import sources.models.*;
import sources.server.MovieCollection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MovieCollectionXMLFileReader implements MovieCollectionFileReader {
    private final String path;

    public MovieCollectionXMLFileReader(String path) {
        this.path = path;
    }

    @Override
    public MovieCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException {
        checkFile();
        String attributeName = null;
        int i = -1;
        try {
            MovieCollection movieCollection = new MovieCollection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Empty file
            File file = new File(path);
            if (file.length() == 0) {
                System.out.println("Created empty movie collection.");
                movieCollection.setCreationDate(ZonedDateTime.now());
                return movieCollection;
            }

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            attributeName = "collectionCreationDate";
            String collectionCreationDateInput = document.getDocumentElement().getAttribute(attributeName);
            ZonedDateTime collectionCreationDate = ZonedDateTime.parse(collectionCreationDateInput);

            attributeName = "movie";
            NodeList movieElements = document.getDocumentElement().getElementsByTagName(attributeName);
            for (i = 0; i < movieElements.getLength(); i++) {
                Element movieElement = (Element) movieElements.item(i);

                attributeName = "id";
                String idInput = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int id = Integer.parseInt(idInput);

                attributeName = "movieName";
                String movieName = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();

                attributeName = "coordinates";
                Element coordinatesInput = (Element) movieElement.getElementsByTagName(attributeName).item(0);
                if (coordinatesInput == null)
                    throw new NullPointerException();
                attributeName = "x";
                String xInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int x = Integer.parseInt(xInput);
                attributeName = "y";
                String yInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int y = Integer.parseInt(yInput);
                Coordinates coordinates = new Coordinates(x, y);

                attributeName = "movieCreationDate";
                String creationDateInput = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                ZonedDateTime creationDate = ZonedDateTime.parse(creationDateInput);

                attributeName = "oscarsCount";
                String oscarsCountInput = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                long oscarsCount = Long.parseLong(oscarsCountInput);

                attributeName = "genre";
                String genreInput = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                MovieGenre movieGenre;
                try {
                    movieGenre = MovieGenre.valueOf(genreInput);
                } catch (IllegalArgumentException e) {
                    throw new WrongArgumentException("wrong movie genre");
                }

                attributeName = "mpaaRating";
                String mpaaRatingInput = movieElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                MpaaRating mpaaRating;
                try {
                    mpaaRating = MpaaRating.valueOf(mpaaRatingInput);
                } catch (IllegalArgumentException e) {
                    throw new WrongArgumentException("wrong MPAA rating");
                }

                attributeName = "director";
                Element directorInput = (Element) movieElement.getElementsByTagName(attributeName).item(0);
                if (directorInput == null)
                    throw new NullPointerException();

                attributeName = "directorName";
                String directorNameInput = directorInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();

                attributeName = "birthday";
                String birthdayInput = directorInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                LocalDateTime birthday = LocalDateTime.parse(birthdayInput);

                attributeName = "weight";
                String weightInput = directorInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Integer weight = Integer.parseInt(weightInput);

                attributeName = "passportID";
                Node passportIDInput = directorInput.getElementsByTagName(attributeName).item(0);
                String passportID = null;
                if (passportIDInput != null && !passportIDInput.getTextContent().trim().equals("")) {
                    passportID = passportIDInput.getTextContent().trim();
                }

                Person director = new Person(directorNameInput, birthday, weight, passportID);

                if (movieCollection.getElementByID(id) != null)
                    throw new WrongArgumentException("movie id must be unique");

                Movie movie = new Movie(id, movieName, coordinates, oscarsCount, movieGenre, mpaaRating, director);
                movie.setCreationDate(creationDate);
                movieCollection.put(id, movie);
            }
            movieCollection.setCreationDate(collectionCreationDate);
            Movie.updateNextId(movieCollection);
            return movieCollection;
        } catch (NullPointerException e) {
            throw new InvalidFileDataException(path, "movie №" + (i + 1) + ": " + attributeName + " is null");
        } catch (DateTimeParseException e) {
            if (i < 0) {
                throw new InvalidFileDataException(path, attributeName + " is invalid or null");
            } else {
                throw new InvalidFileDataException(path, "movie №" + (i + 1) + ": " + attributeName + " is invalid or null");
            }
        } catch (UnsupportedEncodingException e) {
            throw new InvalidFileDataException(path, "unsupported encoding: " + e.getMessage());
        } catch (SAXParseException e) {
            throw new InvalidFileDataException(path, "XML parse error: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidFileDataException(path, "movie №" + (i + 1) + ": " + attributeName + " must be an integer");
        } catch (WrongArgumentException e) {
            StringBuilder errorMessage = new StringBuilder(e.getMessage());
            errorMessage.delete(0, 2);
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new InvalidFileDataException(path, "movie №" + (i + 1) + ": " + errorMessage);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getClass());
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new InvalidFileDataException(path, e.getMessage());
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read permission for file " + path + "  !");
    }
}
