package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ErrorApp {
    
    public static boolean dateHandler(String date){
        LocalDate temp;
        try{
            temp = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-M-d"));
            if (temp.isBefore(LocalDate.now())) return false;
            return true;
        }catch(DateTimeParseException e){
            System.out.println("Invalid date format");
            return false;
        }
    }
}
