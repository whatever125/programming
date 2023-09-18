package common.responses;

import java.io.Serial;

public class HelpResponse extends Response {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String help;

    public HelpResponse(String error, String help) {
        super("help", error);
        this.help = help;
    }
}
