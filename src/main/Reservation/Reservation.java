package main.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Represents the Reservation that can be made.
 */
public class Reservation {
    /**Table number assigned to the reservation. */
    private int table_number;
    /**Date of reservation */
    private LocalDate date;
    /**Time of reservation */
    private LocalTime time;
    /**Number of people */
    private int pax;
    /**Name of reserving person */
    private String name;
    /**Contact of reserving person */
    private int contact;
    
    /**
     * Creates new reservation.
     * @param table_number The number assigned to the table
     * @param date The user input date
     * @param time The user input time
     * @param pax The number of people being reserved a seat at the restaurant
     * @param name The name of the reserving customer
     * @param contact The contact number of the reserving customer
     */
    public Reservation(int table_number, LocalDate date, LocalTime time, int pax, String name, int contact){
        this.table_number = table_number;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
    }
    
    /**
     * Get table number
     * @return table number of current table
     */
    public int getTableNumber(){
        return table_number;
    }

    /**
     * Get date of reservation
     * @return Date of reservation
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Get time of reservation
     * @return Time of reservation
     */
    public LocalTime getTime(){
        return time;
    }

    /**
     * Get number of people of reservation
     * @return Pax of reservation
     */
    public int getPax(){
        return pax;
    }

    /**
     * Get name of reserving person.
     * @return Name of reserving person
     */
    public String getName(){
        return name;
    }

    /**
     * Get contact number of reserving person
     * @return Contact of reserving person.
     */
    public int getContact(){
        return contact;
    }
}
