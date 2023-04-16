package sources.Models.Objects;

import sources.Models.Abstracts.Creature;
import sources.Models.Interfaces.FlyInterface;
import sources.Models.Interfaces.WatchInterface;

public class Beetle extends Creature implements FlyInterface, WatchInterface {
    private final String type;

    public Beetle(String type) {
        super("жук " + type);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void flyOver(Creature creature) {
        System.out.println(this + " налетел на " + creature.getName());
    }

    @Override
    public void flyAway() {
        System.out.println(this + " улетает и скрывается вдали");
    }

    @Override
    public String toString() {
        return type + " жук";
    }
}
