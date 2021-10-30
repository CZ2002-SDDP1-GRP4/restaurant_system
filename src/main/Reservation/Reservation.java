package main.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private int table_number;
    private LocalDate date;
    private LocalTime time;
    private int pax;
    private String name;
    private int contact;
    
    public Reservation(int table_number, LocalDate date, LocalTime time, int pax, String name, int contact){
        this.table_number = table_number;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
    }

    public int getTableNumber(){
        return table_number;
    }

    public LocalDate getDate(){
        return date;
    }
    public LocalTime getTime(){
        return time;
    }
    public int getPax(){
        return pax;
    }
    public String getName(){
        return name;
    }
    public int getContact(){
        return contact;
    }
}
