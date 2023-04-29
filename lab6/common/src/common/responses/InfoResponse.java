package common.responses;

import java.io.Serial;

public class InfoResponse extends Response{
    @Serial
    private static final long serialVersionUID = 1L;
    public final String info;

    public InfoResponse(String error, String info) {
        super("info", error);
        this.info = info;
    }
}
