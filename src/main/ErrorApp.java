package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class used to handle System input from users while handling exceptions.
 */
public class ErrorApp {
    
    /**Scanner object used to take in user input */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Takes in user input and checks if it is correct Date format
     * @return a LocalDate object
     */
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

    /**
     * Takes in user input and ensures its a valid integer.
     * @return integer
     */
    public static int safeInteger() {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    /**
     * Takes in user input and ensures its a valid index for timeslots in ReservationApp.
     * @return integer
     */
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

    /**
     * Takes in user input and ensures it is a valid table capacity.
     * @return integer
     */
    public static int tableInt(){
        while (true){
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
            int input = sc.nextInt(); 
            sc.nextLine();
            if (input % 2 != 0 && input >= 2 && input <= 10) {
                System.out.println("Table capacity must be evevn <= 10 and > 2:");
                continue;
            }
            return input;
        }
    }

    /**
     * Takes in user input and ensures its a valid double.
     * @return double
     */
    public static double safeDouble() {
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }

    /**
     * Takes in user input and ensures its contains only alphabets.
     * @return String
     */
    public static String alphaString(){
       while(!sc.hasNext("[A-Za-z]+$")){
           System.out.println("Please enter only alphabets.");
           sc.nextLine();
       } 
       String string = sc.nextLine();
       return string;
    }
}
