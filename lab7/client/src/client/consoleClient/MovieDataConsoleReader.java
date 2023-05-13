package client.consoleClient;

import common.IOHandlers.BasicReader;
import client.exceptions.InvalidScriptException;
import common.exceptions.WrongArgumentException;
import common.models.MovieGenre;
import common.models.MpaaRating;
import common.models.helpers.MovieArgumentChecker;
import common.models.helpers.PersonArgumentChecker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 The MovieDataConsoleReader class provides static methods for reading movie data from console input.
 The class provides methods for reading movie name, coordinates, oscars count, genre, MPAA rating,
 and director name from console input.
 */
public class MovieDataConsoleReader {
    /**
     * Reads the movie name from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The movie name entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static String readMovieName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String movieName = null;
        while (movieName == null) {
            try {
                String input = basicReader.readLine("Enter movie name");
                movieName = input.trim();
                MovieArgumentChecker.checkName(movieName);
            } catch (WrongArgumentException e) {
                movieName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return movieName;
    }

    /**
     * Reads the X coordinate from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The X coordinate entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Integer readX(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer x = null;
        boolean xSuccess = false;
        while (!xSuccess) {
            try {
                String input = basicReader.readLine("Enter X coordinate");
                String xInput = input.trim();
                x = Integer.parseInt(xInput);
                xSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return x;
    }

    /**
     * Reads the Y coordinate from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The Y coordinate entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Integer readY(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer y = null;
        boolean ySuccess = false;
        while (!ySuccess) {
            try {
                String input = basicReader.readLine("Enter Y coordinate");
                String xInput = input.trim();
                y = Integer.parseInt(xInput);
                ySuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return y;
    }

    /**
     * Reads the oscarsCount from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The oscarsCount entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static long readOscrasCount(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        long oscarsCount = 0;
        boolean oscarsCountSuccess = false;
        while (!oscarsCountSuccess) {
            try {
                String input = basicReader.readLine("Enter oscars count");
                String oscarsCountInput = input.trim();
                oscarsCount = Long.parseLong(oscarsCountInput);
                MovieArgumentChecker.checkOscarsCount(oscarsCount);
                oscarsCountSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                String errorMessage = e.getMessage();
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return oscarsCount;
    }

    /**
     * Reads the movie genre from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The movie genre entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static MovieGenre readMovieGenre(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MovieGenre movieGenre = null;

        StringBuilder message = new StringBuilder("Enter movie genre (");
        for (MovieGenre genre : MovieGenre.values()) {
            message.append(genre.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (movieGenre == null) {
            String input = basicReader.readLine(String.valueOf(message));
            String movieGenreInput = input.trim();
            try {
                movieGenre = MovieGenre.valueOf(movieGenreInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                String errorMessage = "! wrong movie genre !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return movieGenre;
    }

    /**
     * Reads the MPAA rating from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The MPAA rating entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static MpaaRating readMpaaRating(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MpaaRating mpaaRating = null;

        StringBuilder message = new StringBuilder("Enter MPAA rating (");
        for (MpaaRating rating : MpaaRating.values()) {
            message.append(rating.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (mpaaRating == null) {
            String input = basicReader.readLine(String.valueOf(message));
            String mpaaRatingInput = input.trim();
            try {
                mpaaRating = MpaaRating.valueOf(mpaaRatingInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                String errorMessage = "! wrong MPAA rating !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return mpaaRating;
    }

    /**
     * Reads the director name from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The director name entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static String readDirectorName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String directorName = null;
        while (directorName == null) {
            try {
                String input = basicReader.readLine("Enter director name");
                directorName = input.trim();
                PersonArgumentChecker.checkName(directorName);
            } catch (WrongArgumentException e) {
                directorName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return directorName;
    }

    /**
     * Reads the director birthday from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The director birthday entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static LocalDateTime readBirthday(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        LocalDateTime birthday = null;
        while (birthday == null) {
            try {
                String input = basicReader.readLine("Enter director birthday in DD.MM.YYYY format");
                String birthdayInput = input.trim();
                birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay();
            } catch (DateTimeParseException e) {
                String errorMessage = "! wrong date format !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return birthday;
    }

    /**
     * Reads the director weight from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The director weight entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Integer readWeight(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer weight = null;
        boolean weightSuccess = false;
        while (!weightSuccess) {
            try {
                String input = basicReader.readLine("Enter director weight");
                String weightInput = input.trim();
                if (!weightInput.equals("")) {
                    weight = Integer.parseInt(weightInput);
                }
                PersonArgumentChecker.checkWeight(weight);
                weightSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return weight;
    }

    /**
     * Reads the director passport ID from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The director passport ID entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static String readPassportID(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        boolean passportIDSuccess = false;
        String passportID = null;
        while (!passportIDSuccess) {
            try {
                String input = basicReader.readLine("Enter director passport ID");
                passportID = input.trim();
                if (Objects.equals(passportID, "")) {
                    passportID = null;
                }
                PersonArgumentChecker.checkPassportID(passportID);
                passportIDSuccess = true;
            } catch (WrongArgumentException e) {
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return passportID;
    }
}
