package sources.Models.Abstracts;

import sources.Models.Interfaces.ThingInterface;

import java.util.Objects;

public abstract class Thing implements ThingInterface {

    private final String name;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public void spawn() {
        System.out.println("Предмет " + name + " типа " + this.getClass().getName() + " создан");
    }

    @Override
    public void action(String name) {
        System.out.println(name + " существует");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Вещь '" + name + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing thing)) return false;
        return Objects.equals(name, thing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
