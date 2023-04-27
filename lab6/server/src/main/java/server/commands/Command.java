package server.commands;

import common.requests.Request;
import common.responses.Response;

public interface Command {
    Response execute(Request request);
}
