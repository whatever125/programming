package server.commands;

import common.requests.Request;
import common.responses.InfoResponse;
import common.responses.InsertResponse;
import common.responses.Response;
import server.Executor;

public class Info extends AbstractCommandWithResult<String> {
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
