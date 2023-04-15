package sources.characters;

import sources.models.AbstractBeetle;
import sources.models.AbstractHuman;
import sources.models.Place;
import sources.models.Damageable;

public class MayBeetle extends AbstractBeetle {

    public MayBeetle() {
        super("жук", "майский");
    }

    @Override
    public void act(Place place) {
        switch (place) {
            case FIELD -> System.out.println("В это время летел майский жук. ");
            case FAR_AWAY -> System.out.println("и скрылся вдали. ");
            default -> System.out.println("Действие МАЙСКОГО ЖУКА для МЕСТА " + place.name() + " не определено");
        }
    }

    public void flyOver(AbstractHuman human) {
        if (!canSee()) {
            System.out.println("Он сослепу налетел на " + human.getName());
            applyDamage((Damageable) human);
        }
    }

    public void flyAway() {
        System.out.println("Жук в ту же минуту улетел");
    }

    @Override
    public void getDamage() {

    }

    @Override
    public void applyDamage(Damageable target) {
        System.out.println("и ударил его по затылку. ");
        target.getDamage();
    }

    @Override
    public boolean canSee() {
        return isAbleToSee();
    }

    @Override
    public void watch() {

    }
}
