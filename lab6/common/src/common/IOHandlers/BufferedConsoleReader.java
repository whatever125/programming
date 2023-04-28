package common.IOHandlers;

import common.exceptions.EndOfInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BufferedConsoleReader implements BasicReader {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Scanner scanner = new Scanner(reader);

    @Override
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException();
        }
    }

    @Override
    public String readLine(String message) {
        try {
            System.out.print(message + " > ");
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException();
        }
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public boolean ready() throws IOException {
        return reader.ready();
    }
}
