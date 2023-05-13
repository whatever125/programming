package common.models.helpers;

import common.exceptions.WrongArgumentException;

import java.time.LocalDateTime;

public class PersonArgumentChecker extends ArgumentChecker {
    public static void checkArguments(String name, LocalDateTime birthday, Integer weight, String passportID) throws WrongArgumentException {
        PersonArgumentChecker.checkName(name);
        PersonArgumentChecker.checkBirthday(birthday);
        PersonArgumentChecker.checkWeight(weight);
        PersonArgumentChecker.checkPassportID(passportID);
    }

    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!name.equals(""), "parameter name cannot be empty");
    }

    public static void checkBirthday(LocalDateTime birthday) throws WrongArgumentException {
        checkNull(birthday, "birthday");
    }

    public static void checkWeight(Integer weight) throws WrongArgumentException {
        checkNull(weight, "weight");
        checkArgument(weight > 0, "argument weight cannot be <= 0");
    }

    public static void checkPassportID(String passportID) throws WrongArgumentException {
        if (passportID != null) {
            checkArgument(7 <= passportID.length(), "argument passportID length cannot be < 7");
            checkArgument(passportID.length() <= 32, "argument passportID length cannot be > 32");
        }
    }
}
