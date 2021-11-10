package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ErrorApp {

    public static boolean dateHandler(String date) {
        LocalDate temp;
        try {
            temp = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-M-d"));
            if (temp.isBefore(LocalDate.now())) {
                System.out.println("Date cannot be before current date");
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
            return false;
        }
    }

    public static int safeInteger() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public static double safeDouble() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }
}
