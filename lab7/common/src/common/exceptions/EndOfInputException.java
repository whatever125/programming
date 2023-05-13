package common.exceptions;

public class EndOfInputException extends RuntimeException {
    public EndOfInputException() {
        super("! unexpected end of input !");
    }
}
