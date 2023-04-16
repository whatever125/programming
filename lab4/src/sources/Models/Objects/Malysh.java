package sources.Models.Objects;

import java.util.ArrayList;
import java.util.List;

import sources.Enums.Place;
import sources.Models.Objects.RoomStorage.Room;
import sources.Models.Abstracts.Creature;
import sources.Models.Abstracts.Thing;

public class Malysh extends Creature {
    private House house;
    private Room room;
    private Thing clothes;
    private Thing pants;
    private Thing hat;
    private double popularity;
    private int intelligence;
    private final List<Malysh> brothers = new ArrayList<>();
    private final List<Malysh> friends = new ArrayList<>();


    public Malysh(String name) {
        super(name);
    }

    public Thing getClothes() {
        return clothes;
    }

    public void setClothes(Thing clothes) {
        this.clothes = clothes;
        System.out.println(getName() + " надел на себя " + clothes);
    }

    public Thing getPants() {
        return pants;
    }

    public void setPants(Thing pants) {
        this.pants = pants;
        System.out.println(getName() + " надел на ноги " + pants);
    }

    public Thing getHat() {
        return hat;
    }

    public void setHat(Thing hat) {
        this.hat = hat;
        System.out.println(getName() + " надел на голову " + hat);
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
        System.out.println(getName() + " теперь известен и популярен на " + getPopularity() * 100 + "%");
    }

    public List<Malysh> getBrothers() {
        return brothers;
    }

    public void makeBrothers(Malysh brother) {
        this.brothers.add(brother);
        brother.brothers.add(this);
        System.out.println("Теперь " + getName() + " и " + brother.getName() + " братья");
    }

    public List<Malysh> getFriends() {
        return friends;
    }

    public void makeFriends(Malysh friend) {
        this.brothers.add(friend);
        friend.friends.add(this);
        System.out.println("Теперь " + getName() + " и " + friend.getName() + " друзья");
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        System.out.println(getName() + " теперь обладает IQ " + getIntelligence());
    }

    @Override
    public void act(Place place) {
        switch (place) {
            case CITY -> System.out.println(getName() + " гуляет по городу");
            case FIELD -> System.out.println(getName() + "попадает в поле");
            case GROUND -> System.out.println(getName() + " вскакивает с земли");
            case UP -> {
                if (canSee()) {
                    System.out.println(getName() + " посмотрел наверх и что-то увидел");
                } else {
                    System.out.println(getName() + " задрал голову и поглядел вверх, но ничего не увидел");
                }
            }
            default -> System.out.println("Действие " + this.getClass().getName() + " для места " + place.name() + " не определено");
        }
    }
}
