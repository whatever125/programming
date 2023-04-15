package sources.characters;

import sources.models.Place;
import sources.models.Creatureable;

public class Nobody implements Creatureable {

    public Nobody() {
        System.out.println("Объект НИКТО создан");
    }

    public void act(Place place) {
        switch (place) {
            case VOKRUG -> System.out.println("Вокруг не было ни души. ");
            case KRUGOM -> System.out.println("Но кругом никого не было. ");
            default -> System.out.println("Действие НИКОГО для МЕСТА " + place.name() + " не определено");
        }
    }

    @Override
    public void becomeFamous(double popularity) {
        if (popularity > 0.99) {
            System.out.println("В особенности никто не прославился. ");
        }
    }
}
