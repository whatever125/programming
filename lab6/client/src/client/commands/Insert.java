package client.commands;

import client.Client;
import common.requests.InsertRequest;
import common.requests.Request;
import client.exceptions.WrongArgumentException;
import common.models.MovieGenre;
import common.models.MpaaRating;
import server.QueryHandler;
import server.Executor;
import server.exceptions.CollectionKeyException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class Insert extends AbstractCommand {
    private final Integer key;
    private final String movieName;
    private final Integer x;
    private final Integer y;
    private final long oscarsCount;
    private final MovieGenre movieGenre;
    private final MpaaRating mpaaRating;
    private final String directorName;
    private final LocalDateTime birthday;
    private final Integer weight;
    private final String passportID;

    public Insert(Client client, Executor executor, Integer key, String movieName, Integer x,
                  Integer y, long oscarsCount, MovieGenre movieGenre, MpaaRating mpaaRating, String directorName,
                  LocalDateTime birthday, Integer weight, String passportID) {
        super("insert", client, executor);
        this.key = key;
        this.movieName = movieName;
        this.x = x;
        this.y = y;
        this.oscarsCount = oscarsCount;
        this.movieGenre = movieGenre;
        this.mpaaRating = mpaaRating;
        this.directorName = directorName;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
    }

    @Override
    public void execute() throws CollectionKeyException, WrongArgumentException {
        try {
            Request request = new InsertRequest(key, movieName, x, y, oscarsCount,
                    movieGenre, mpaaRating, directorName, birthday, weight, passportID);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(request);
            oos.close();

            new QueryHandler(executor).handle(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                ", movieName='" + movieName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", oscarsCount=" + oscarsCount +
                ", movieGenre=" + movieGenre +
                ", mpaaRating=" + mpaaRating +
                ", directorName='" + directorName + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}
