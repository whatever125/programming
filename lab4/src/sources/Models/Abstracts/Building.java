package sources.Models.Abstracts;

import sources.Models.Interfaces.RoomStorageInterface;
import sources.Models.Objects.Malysh;
import sources.Models.Objects.RoomStorage;
import sources.Models.Objects.RoomStorage.Room;

import java.util.List;
import java.util.Objects;

public abstract class Building {
    private String street;
    private int number;
    private final RoomStorageInterface roomStorageInterface = new RoomStorage(this);

    public Building() {
        System.out.println("Строение типа " + this.getClass().getName() + " создано");
    }

    public Building(String street) {
        this.street = street;
        System.out.println("Строение типа " + this.getClass().getName() + " по адресу " + street + " создан");
    }

    public Building(String street, int number) {
        this.street = street;
        this.number = number;
        System.out.println("Строение типа " + this.getClass().getName() + " по адресу " + street + ", " + number + " создан");
    }

    public List<Room> getRooms() {
        return roomStorageInterface.getRooms();
    }

    public void addRoom(Malysh owner) {
        roomStorageInterface.addRoom(owner);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Строение типа " + this.getClass().getName() + " по адресу " + street + " " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building building)) return false;
        return number == building.number && Objects.equals(street, building.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }
}
