package sources.Models.Objects;

import sources.Models.Abstracts.Building;
import sources.Models.Abstracts.Thing;
import sources.Models.Interfaces.RoomStorageInterface;

import java.util.ArrayList;
import java.util.List;

public class RoomStorage implements RoomStorageInterface {
    private final List<Room> rooms = new ArrayList<>();
    private final Building building;

    public RoomStorage(Building building) {
        this.building = building;
    }

    public class Room {
        private Malysh owner;
        private final List<Thing> books = new ArrayList<>();
        private double freeSpace = 100;

        public Room(Malysh owner) {
            this.owner = owner;
            owner.setRoom(this);
        }

        public Malysh getOwner() {
            return owner;
        }

        public void setOwner(Malysh owner) {
            this.owner = owner;
            owner.setRoom(this);
        }

        public List<Thing> getBooks() {
            return books;
        }

        public void addBook(Thing book) {
            if (freeSpace > 0) {
                freeSpace -= 1;
                books.add(book);
            }
        }

        public double getFreeSpace() {
            return freeSpace;
        }

        public void setFreeSpace(double freeSpace) {
            this.freeSpace = freeSpace;
        }

        @Override
        public String toString() {
            return "Комната, владелец " + owner.getName() + ", находится в " + building;
        }
    }

    @Override
    public void addRoom(Malysh owner) {
        rooms.add(new Room(owner));
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }
}
