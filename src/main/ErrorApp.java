package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ErrorApp {
    
    public static Scanner sc = new Scanner(System.in);

    public static LocalDate dateHandler() {
        LocalDate temp;
        while (true){
            try {
                temp = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("uuuu-M-d"));
                if (temp.isBefore(LocalDate.now())) {
                    System.out.println("Date cannot be before current date");
                    continue;
                }
                return temp;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format");
                continue;
            }

        }
    }

    public static int safeInteger() {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public static int safeTimeSlot(){
        while (true){
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
            int input = sc.nextInt(); 
            sc.nextLine();
            if (!(input <= 12 && input >= 1)) {
                System.out.println("Please enter valid timeslot choice:");
                continue;
            }
            return input;
        }
    }

    public static double safeDouble() {
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }

    public static String alphaString(){
       while(!sc.hasNext("[A-Za-z]+$")){
           System.out.println("Please enter only alphabets.");
           sc.nextLine();
       } 
       String string = sc.nextLine();
       return string;
    }
}
