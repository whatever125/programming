package server.handlers;

import common.requests.Request;
import common.responses.Response;

public interface CommandHandler {
    Response handle(Request request);
}
