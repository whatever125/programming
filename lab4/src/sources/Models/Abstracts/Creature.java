package sources.Models.Abstracts;

import sources.Enums.Place;
import sources.Models.Interfaces.CreatureInterface;
import sources.Models.Interfaces.DamageInterface;
import sources.Models.Interfaces.WatchInterface;

import java.util.Objects;

public abstract class Creature implements CreatureInterface, WatchInterface, DamageInterface {
    private String name;
    private int age;
    private String gender;
    private double health = 1;
    private boolean isAbleToSee = Math.random() >= 0.5;

    public Creature() {
        System.out.println("Существо типа " + this.getClass().getName() + " создано");
    }

    public Creature(String name) {
        this.name = name;
        System.out.println("Существо типа " + this.getClass().getName() + " с именем " + name + " создано");
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void act(Place place) {
        System.out.println("Действие " + this.getClass().getName() + " для места " + place.name() + " не определено");
    }

    @Override
    public void act(String action) {
        System.out.println(name + " совершает действие: " + action);
    }

    @Override
    public void talk(String phrase) {
        System.out.println(name + " сказал: " + phrase);
    }

    @Override
    public void think(String phrase) {
        System.out.println(name + " подумал: " + phrase);
    }

    @Override
    public boolean canSee() {
        if (isAbleToSee) {
            System.out.println(name + " все видит");
            isAbleToSee = Math.random() >= 0.5;
            return true;
        } else {
            System.out.println(name + " ничего и никого не видит");
            isAbleToSee = Math.random() >= 0.5;
            return false;
        }
    }

    public void setAbleToSee(boolean ableToSee) {
        isAbleToSee = ableToSee;
    }

    @Override
    public void lookAround() {
        System.out.println(name + " осматривается по сторонам");
    }

    @Override
    public void getDamage(String damage) {
        health = Math.round(Math.random() * health * 100.0) / 100.0;
        System.out.println(name + " получил урон: " + damage + "; осталось " + health * 100 + "% здоровья");
    }

    @Override
    public void applyDamage(DamageInterface target, String damage) {
        target.getDamage(damage);
    }

    @Override
    public void getSightDamage(String damage) {
        health = Math.round(Math.random() * health * 100.0) / 100.0;
        if (health < 0.5) {
            setAbleToSee(false);
            System.out.println(name + " получил урон: " + damage + ", потеря зрения; осталось " + health * 100 + "% здоровья");
        } else {
            setAbleToSee(true);
            System.out.println(name + " получил урон: " + damage + "; осталось " + health * 100 + "% здоровья");
        }
    }

    @Override
    public void applySightDamage(DamageInterface target, String damage) {
        target.getSightDamage(damage);
    }

    @Override
    public String toString() {
        return "Существо типа " + this.getClass().getName() + " по имени " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creature creature)) return false;
        return age == creature.age && Objects.equals(name, creature.name) && Objects.equals(gender, creature.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }
}
