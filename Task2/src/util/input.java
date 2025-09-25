package util;

import java.util.Scanner;

public class input {
    private static Scanner scanner = new Scanner(System.in);

    // Get integer input from user
    public static int getInt(String prompt) {
        System.out.print(prompt + ": ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Get string input from user
    public static String getString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
}
