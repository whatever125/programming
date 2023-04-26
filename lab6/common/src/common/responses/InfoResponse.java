package common.responses;

public class InfoResponse extends Response{
    public final String info;

    public InfoResponse(String error, String info) {
        super("info", error);
        this.info = info;
    }
}
