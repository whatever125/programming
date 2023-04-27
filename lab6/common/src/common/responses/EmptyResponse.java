package common.responses;

import java.io.Serial;

public class EmptyResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public EmptyResponse() {
        super(null, null);
    }
}
