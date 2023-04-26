package common.responses;

public class InsertResponse extends Response {
    public InsertResponse(String error) {
        super("insert", error);
    }
}
