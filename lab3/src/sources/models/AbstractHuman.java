package sources.models;

import java.util.Objects;

public abstract class AbstractHuman implements Damageable, Creatureable, Seeable {
    private String name;
    private int age;
    private double popularity;
    private String gender;

    public AbstractHuman(String name) {
        this.name = name;
        System.out.println("Объект " + name.toUpperCase() + " создан");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AbstractHuman{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractHuman that)) return false;

        if (age != that.age) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
