package common.responses;

public class ExitResponse extends Response {
    public ExitResponse(String error) {
        super("exit", error);
    }
}
