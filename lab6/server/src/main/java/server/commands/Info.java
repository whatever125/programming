package server.commands;

import common.requests.Request;
import common.responses.InfoResponse;
import server.handlers.Executor;

import java.io.Serial;

public class Info extends AbstractCommandWithResult<String> {
    @Serial
    private static final long serialVersionUID = 1L;
    private String result = null;

    public Info(Executor executor) {
        super("info", executor);
    }

    @Override
    public InfoResponse execute(Request request) {
        result = executor.info();
        return new InfoResponse(null, result);
    }

    @Override
    public String getResult() {
        return result;
    }
}
