package sources.Models.Objects;

import sources.Models.Abstracts.Creature;

public class Dog extends Creature {
    private Creature owner;

    public Dog(String name, Creature owner) {
        super(name);
        this.owner = owner;
    }

    public Creature getOwner() {
        return owner;
    }

    public void setOwner(Creature owner) {
        this.owner = owner;
    }
}
