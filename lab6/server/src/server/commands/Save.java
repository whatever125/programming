package server.commands;

import common.requests.Request;
import server.Executor;

public class Save extends AbstractCommand {

    public Save(Executor executor) {
        super("save", executor);
    }

    @Override
    public void execute(Request request) {
        executor.save();
    }
}
