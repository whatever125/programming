package common.responses;

import java.io.Serial;

public class ReplaceIfLoweResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final Boolean replaced;

    public ReplaceIfLoweResponse(String error, Boolean replaced) {
        super("replace_if_lowe", error);
        this.replaced = replaced;
    }
}
