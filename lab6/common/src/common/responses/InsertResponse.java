package common.responses;

import java.io.Serial;

public class InsertResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public InsertResponse(String error) {
        super("insert", error);
    }
}
