package main.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Table {
    private int table_number;
    private int capacity;
    private boolean available;
    private ArrayList <Reservation> reservations;
    //private Order order;

    public Table(int table_number, int capacity){
        this.table_number = table_number;
        this.capacity = capacity;
        this.available = true;
        reservations = new ArrayList<Reservation>();
        // order = new Order();
    }

    public int getTableNumber(){
        return table_number;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean getAvailability(){
        return available;
    }

    public ArrayList<Reservation> getReservations(){
        return reservations;
    }

}
