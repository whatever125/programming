package sources.models;

import java.util.Objects;

public abstract class AbstractBeetle implements Damageable, Creatureable, Seeable, Flyable {
    private String name;
    private String type;
    private boolean ableToSee;

    public AbstractBeetle(String name, String type) {
        this.name = name;
        this.type = type;
        System.out.println("Объект " + type.toUpperCase() + " " + name.toUpperCase() + " создан");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAbleToSee() {
        return ableToSee;
    }

    public void setAbleToSee(boolean ableToSee) {
        this.ableToSee = ableToSee;
    }

    @Override
    public void becomeFamous(double popularity) {
        if (popularity > 0.99) {
            System.out.println("В особенности жук прославился после одной истории. ");
        }
    }

    @Override
    public String toString() {
        return "AbstractBeetle{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBeetle that)) return false;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
