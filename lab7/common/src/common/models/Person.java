package common.models;

import common.models.helpers.PersonArgumentChecker;
import common.exceptions.WrongArgumentException;

import java.io.Serial;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 32, Длина строки должна быть не меньше 7, Значение этого поля должно быть уникальным, Поле может быть null

    public Person(String name, LocalDateTime birthday, Integer weight, String passportID) throws WrongArgumentException {
        PersonArgumentChecker.checkArguments(name, birthday, weight, passportID);
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws WrongArgumentException {
        PersonArgumentChecker.checkName(name);
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) throws WrongArgumentException {
        PersonArgumentChecker.checkBirthday(birthday);
        this.birthday = birthday;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) throws WrongArgumentException {
        PersonArgumentChecker.checkWeight(weight);
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) throws WrongArgumentException {
        PersonArgumentChecker.checkPassportID(passportID);
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}
