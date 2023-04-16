package sources.Models.Interfaces;

import sources.Models.Objects.Malysh;
import sources.Models.Objects.RoomStorage.Room;

import java.util.List;

public interface RoomStorageInterface {
    void addRoom(Malysh owner);
    List<Room> getRooms();
}
