package common.responses;

public class UpdateResponse extends Response {
    public UpdateResponse(String error) {
        super("update", error);
    }
}
