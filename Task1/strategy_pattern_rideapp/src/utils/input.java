package utils;

import java.util.Scanner;

public class input {
    private static Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}

