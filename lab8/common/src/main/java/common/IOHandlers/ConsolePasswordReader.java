package common.IOHandlers;

import java.io.Console;

public class ConsolePasswordReader implements BasicReader {
    private final Console console = System.console();

    @Override
    public String readLine() {
        System.out.print("$ ");
        char[] password = console.readPassword();
        return new String(password);
    }

    @Override
    public String readLine(String message) {
        System.out.print(message + " > ");
        char[] password = console.readPassword();
        return new String(password);
    }

    @Override
    public boolean hasNextLine() {
        // TODO: 16/5/2023 implement 
        return false;
    }
}
