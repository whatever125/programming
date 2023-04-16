package sources.commands;

import sources.client.Client;
import sources.exceptions.io.WrongArgumentException;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.receiver.Receiver;

import java.time.LocalDateTime;

public class RemoveGreater extends AbstractCommand {
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

    public RemoveGreater(Client client, Receiver receiver, String movieName, Integer x, Integer y,
                         long oscarsCount, MovieGenre movieGenre, MpaaRating mpaaRating, String directorName,
                         LocalDateTime birthday, Integer weight, String passportID) {
        super("remove_greater", client, receiver);
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
    public void execute() throws WrongArgumentException {
        receiver.removeGreater(movieName, x, y, oscarsCount, movieGenre,
                mpaaRating, directorName, birthday, weight, passportID);
    }

    @Override
    public String toString() {
        return name + " {" +
                "movieName='" + movieName + '\'' +
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
