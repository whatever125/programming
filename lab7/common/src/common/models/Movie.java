package common.models;

import common.models.helpers.MovieArgumentChecker;
import common.exceptions.WrongArgumentException;

import java.io.Serial;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Movie implements Comparable<Movie>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static Integer nextID = 1;

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле не может быть null

    public Movie(String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating,
                 Person director) throws WrongArgumentException {
        MovieArgumentChecker.checkArguments(name, coordinates, oscarsCount, genre, mpaaRating, director);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public static void updateID() {
        nextID += 1;
    }

    public static void updateMaxId(ArrayList<Movie> movies) {
        int maxID = movies
                .stream().filter(Objects::nonNull)
                .map(Movie::getID)
                .mapToInt(Integer::intValue).max().orElse(0);
        nextID = maxID + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws WrongArgumentException {
        MovieArgumentChecker.checkName(name);
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws WrongArgumentException {
        MovieArgumentChecker.checkCoordinates(coordinates);
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(long oscarsCount) throws WrongArgumentException {
        MovieArgumentChecker.checkOscarsCount(oscarsCount);
        this.oscarsCount = oscarsCount;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) throws WrongArgumentException {
        MovieArgumentChecker.checkGenre(genre);
        this.genre = genre;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) throws WrongArgumentException {
        MovieArgumentChecker.checkMpaaRating(mpaaRating);
        this.mpaaRating = mpaaRating;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) throws WrongArgumentException {
        MovieArgumentChecker.checkDirector(director);
        this.director = director;
    }

    @Override
    public int compareTo(Movie movie) {
        int nameCompare = this.name.toLowerCase().compareTo(movie.name.toLowerCase());
        if (nameCompare == 0)
            return (int) (this.getOscarsCount() - movie.getOscarsCount());
        return this.name.toLowerCase().compareTo(movie.name.toLowerCase());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", director=" + director +
                '}';
    }
}
