package sources.Exceptions;

import java.util.NoSuchElementException;

public class NoSuchEventException extends NoSuchElementException {
    public NoSuchEventException() {
        super("Такого события в списке нет");
    }
}
