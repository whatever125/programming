package common.models.helpers;

import common.exceptions.WrongArgumentException;

public class ArgumentChecker {
    public static void checkNull(Object argument, String argumentName) throws WrongArgumentException {
        checkArgument(argument != null, "argument " + argumentName + " cannot be null");
    }

    public static void checkArgument(boolean statement, String message) throws WrongArgumentException {
        if (!statement) {
            throw new WrongArgumentException(message);
        }
    }
}
