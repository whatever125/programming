package client.commands;

import client.consoleClient.Client;
import client.network.NetworkClient;
import common.exceptions.WrongArgumentException;
import common.requests.InsertRequest;
import common.requests.Request;
import common.requests.UpdateRequest;
import common.responses.InsertResponse;
import common.responses.Response;
import common.responses.UpdateResponse;
import server.exceptions.CollectionKeyException;
import common.models.MovieGenre;
import common.models.MpaaRating;

import java.time.LocalDateTime;

public class Update extends AbstractCommand {
    private final Integer id;
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

    public Update(Client client, NetworkClient networkClient, Integer id, String movieName, Integer x, Integer y,
                  long oscarsCount, MovieGenre movieGenre, MpaaRating mpaaRating, String directorName,
                  LocalDateTime birthday, Integer weight, String passportID) {
        super("update", client, networkClient);
        this.id = id;
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
    public UpdateResponse execute() {
        UpdateRequest request = new UpdateRequest(id, movieName, x, y, oscarsCount,
                movieGenre, mpaaRating, directorName, birthday, weight, passportID);
        UpdateResponse response = (UpdateResponse) networkClient.sendRequest(request);
        return response;
    }

    @Override
    public String toString() {
        return name + " {" +
                "id=" + id +
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
