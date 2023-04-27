package common.responses;

import java.io.Serial;

public class SaveResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public SaveResponse(String error) {
        super("save", error);
    }
}
