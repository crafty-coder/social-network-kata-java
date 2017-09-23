package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.delivery.Console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleShell implements Console {

    private final PrintStream out;
    private final Scanner scanner;

    public ConsoleShell(InputStream in, PrintStream out) {
        this.out = out;
        scanner = new Scanner(in);
    }

    @Override
    public void println(String line) {
        out.println(line);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
