package sources.characters;

import sources.models.AbstractHuman;
import sources.models.Place;
import sources.models.Damageable;

public class Neznaika extends AbstractHuman {

    public Neznaika() {
        super("Незнайка");
    }

    @Override
    public void act(Place place) {
        switch (place) {
            case CITY -> System.out.println("Однажды он гулял по городу ");
            case FIELD -> System.out.println("и забрел в поле. ");
            case GROUND -> {
                System.out.println("Незнайка вскочил, ");
                watch();
            }
            default -> System.out.println("Действие НЕЗНАЙКИ для МЕСТА " + place.name() + " не определено");
        }
    }

    @Override
    public void getDamage() {
        System.out.println("Незнайка кубарем покатился на землю. ");
    }

    @Override
    public void applyDamage(Damageable target) {

    }

    @Override
    public boolean canSee() {
        return true;
    }

    @Override
    public void watch() {
        System.out.println("стал оглядываться по сторонам и смотреть, кто это его ударил. ");
    }

    public void becomeFamous(double popularity) {
        setPopularity(popularity);
        if (popularity > 0.99) {
            System.out.println("В особенности Незнайка прославился после одной истории. ");
        }
    }
}
