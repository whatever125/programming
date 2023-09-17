package common.requests;

import java.io.Serial;

public class SaveRequest extends Request {
    @Serial
    private static final long serialVersionUID = 1L;
    public SaveRequest() {
        // TODO: 17/5/2023 redundant strings
        super("save", "", "");
    }
}
