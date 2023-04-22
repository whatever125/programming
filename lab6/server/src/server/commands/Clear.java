package server.commands;

import common.requests.Request;
import server.Executor;

public class Clear extends AbstractCommand {
    public Clear(Executor executor) {
        super("clear", executor);
    }

    @Override
    public void execute(Request request) {
        executor.clear();
    }
}
