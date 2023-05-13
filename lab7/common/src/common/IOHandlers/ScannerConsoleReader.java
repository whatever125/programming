package common.IOHandlers;

import common.exceptions.EndOfInputException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerConsoleReader implements BasicReader {
    private final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    @Override
    public String readLine() {
        try {
            System.out.print("$ ");
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
}
