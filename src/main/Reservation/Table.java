package main.Reservation;

import java.util.ArrayList;

/**Represents the Table object in the restaurant */
public class Table {
    private int table_number;
    private int capacity;
    private boolean available;
    private ArrayList <Reservation> reservations;
    //private Order order;

    /**
     * Create new table object
     * @param table_number Number of the table.
     * @param capacity Capacity of the table.
     */
    public Table(int table_number, int capacity){
        this.table_number = table_number;
        this.capacity = capacity;
        this.available = true;
        reservations = new ArrayList<Reservation>();
        // order = new Order();
    }

    /**
     * Get table number
     * @return Table number
     */
    public int getTableNumber(){
        return table_number;
    }

    /**
     * Get capacity of table
     * @return Capacity of table
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Get availability status of table
     * @return Availability of table
     */
    public boolean getAvailability(){
        return available;
    }

    /**
     * Get list of reservations for the table
     * @return ArrayList of reservations
     */
    public ArrayList<Reservation> getReservations(){
        return reservations;
    }
    
    /**
     * Set available attribute
     * @param available Status to update table availability
     */
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
