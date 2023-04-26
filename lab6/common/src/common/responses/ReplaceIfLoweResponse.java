package common.responses;

public class ReplaceIfLoweResponse extends Response {
    public final Boolean replaced;

    public ReplaceIfLoweResponse(String error, Boolean replaced) {
        super("replace_if_lowe", error);
        this.replaced = replaced;
    }
}
