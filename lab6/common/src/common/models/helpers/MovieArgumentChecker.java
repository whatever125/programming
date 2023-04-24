package common.models.helpers;

import common.exceptions.WrongArgumentException;
import common.models.Coordinates;
import common.models.MovieGenre;
import common.models.MpaaRating;
import common.models.Person;

import java.util.Objects;

public class MovieArgumentChecker extends ArgumentChecker {
    public static void checkArguments(String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person director) throws WrongArgumentException {
        MovieArgumentChecker.checkName(name);
        MovieArgumentChecker.checkCoordinates(coordinates);
        MovieArgumentChecker.checkOscarsCount(oscarsCount);
        MovieArgumentChecker.checkGenre(genre);
        MovieArgumentChecker.checkMpaaRating(mpaaRating);
        MovieArgumentChecker.checkDirector(director);
    }

    public static void checkArguments(Integer id, String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person director) throws WrongArgumentException {
        MovieArgumentChecker.checkID(id);
        MovieArgumentChecker.checkName(name);
        MovieArgumentChecker.checkCoordinates(coordinates);
        MovieArgumentChecker.checkOscarsCount(oscarsCount);
        MovieArgumentChecker.checkGenre(genre);
        MovieArgumentChecker.checkMpaaRating(mpaaRating);
        MovieArgumentChecker.checkDirector(director);
    }

    public static void checkID(Integer id) throws WrongArgumentException {
        checkNull(id, "id");
        checkArgument(id > 0, "argument id cannot be <= 0");
    }

    public static void checkKey(Integer key) throws WrongArgumentException {
        checkNull(key, "key");
        checkArgument(key > 0, "argument key cannot be <= 0");
    }

    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!Objects.equals(name, ""), "argument name cannot be empty");
    }

    public static void checkCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkNull(coordinates, "coordinates");
    }

    public static void checkOscarsCount(long oscarsCount) throws WrongArgumentException {
        checkArgument(oscarsCount > 0, "argument oscarsCount cannot be <= 0");
    }

    public static void checkGenre(MovieGenre genre) throws WrongArgumentException {
        checkNull(genre, "genre");
    }

    public static void checkMpaaRating(MpaaRating mpaaRating) throws WrongArgumentException {
        checkNull(mpaaRating, "mpaaRating");
    }

    public static void checkDirector(Person director) throws WrongArgumentException {
        checkNull(director, "director");
    }
}
