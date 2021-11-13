package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import main.Reservation.Reservation;
import main.Reservation.Table;

/**
 * Represents the app class for interfacing with reservation and table
 * functions.
 * 
 * @author Brendan Ang
 * @version 1.0
 */
public class ReservationApp {
    /** The list of tables which the restaurant has. */
    private static ArrayList<Table> tables;
    /** Expiry time constant in minutes */
    private static final int expiryTime = 10;
    /** Start time of available reservation timeslots. */
    private String[] timeslots = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
            "17:00", "18:00", "19:00", };

    /** Create new reservation app with list of tables. */
    public ReservationApp() {
        tables = new ArrayList<Table>();
    }

    /**
     * Driver UI code for check and remove reservations
     */
    public void checkRemoveReservations() {

        // remove expired reservations
        removeExpiredReservations();
        System.out.println("1. Check Reservation \n" + "2. Remove Reservation\n" + "Enter selection:");
        int choice = ErrorApp.safeInteger();

        LocalDate date;
        LocalTime time;
        int counter;
        String name;
        System.out.println("Enter name:");
        name = ErrorApp.alphaString();

        System.out.println("Input date (eg. 2021-07-30):");
        date = ErrorApp.dateHandlerBefore();
        System.out.println("Select timeslot:");
        counter = 0;
        for (String timeString : timeslots) {
            counter++;
            System.out.println(counter + ". " + timeString);
        }

        time = LocalTime.parse(timeslots[ErrorApp.safeTimeSlot() - 1]);
        switch (choice) {
        case 1:
            Reservation reserved = checkReservation(name, date, time);
            if (reserved != null) {
                System.out.println("Reservation found:\n" + "Name:" + reserved.getName() + "\n" + "Table Number:"
                        + reserved.getTableNumber());
            } else {
                System.out.println("No reservation found for this date & time.");
            }
            break;

        case 2:
            removeReservation(checkReservation(name, date, time));
            break;
        default:
            break;
        }
    }

    /**
     * Returns the Reservation object if it exists.
     * 
     * @param name Name of reserving person.
     * @param date Date of reservation.
     * @param time Time of reservation.
     * @return Reservation object or null.
     */
    public static Reservation checkReservation(String name, LocalDate date, LocalTime time) {
        String temp = String.valueOf(time.getHour()) + ":00:00";
        if (time.getHour() <= 9)
            temp = "0" + String.valueOf(temp); // for 8am and 9am that dont have the zero in front, this is dumb but
                                               // will work for now
        LocalTime thisSlot = LocalTime.parse(temp);

        for (Table table : tables) {
            for (Reservation reservation : table.getReservations()) {

                String rName = reservation.getName();
                LocalDate rDate = reservation.getDate();
                LocalTime rTime = reservation.getTime();

                if (rName.equals(name) && rDate.isEqual(date) && rTime.equals(thisSlot)) {
                    return reservation;
                }
            }
        }
        return null;
    }

    /**
     * Remove reservations which have expired.
     */
    public void removeExpiredReservations() {

        for (Table table : tables) {
            List<Reservation> found = new ArrayList<Reservation>();
            for (Reservation reservation : table.getReservations()) {
                LocalDate rDate = reservation.getDate();
                LocalTime rTime = reservation.getTime();
                LocalDate today = LocalDate.now();
                LocalTime now = LocalTime.now();
                if (rDate.isBefore(today)) {
                    found.add(reservation);
                } else if (rDate.isEqual(today) && now.isAfter(rTime.plusMinutes(expiryTime))) {
                    found.add(reservation);
                }
            }
            table.getReservations().removeAll(found);
        }
    }

    /**
     * Removes the Reservation object from a table
     * 
     * @param reservation Reservation object to be removed.
     */
    public void removeReservation(Reservation reservation) {
        if (reservation == null) {
            System.out.println("Nothing to remove");
        } else {
            int table_number = reservation.getTableNumber();
            for (Table table : tables) {
                if (table.getTableNumber() == table_number) {
                    table.getReservations().remove(reservation);
                    System.out.println("Reservation removed!");
                }
            }
        }
    }

    /**
     * Creates a reservation
     * 
     * @param table_number Table number
     * @param date         Date of reservation.
     * @param time         Time of reservation.
     * @param pax          Number of people.
     * @param name         Name of reserving person.
     * @param contact      Contact of reserving person.
     */
    public void createReservation(int table_number, LocalDate date, LocalTime time, int pax, String name, int contact) {
        Table tb = getTable(table_number);
        tb.getReservations().add(new Reservation(table_number, date, time, pax, name, contact));
        System.out.println("Reservation created!");
    }

    /**
     * Driver UI code to create reservations
     */
    public void createReservation() {

        LocalDate date;
        LocalTime time;
        int counter, pax, table;
        String name;
        System.out.println("Input date (eg. 2021-07-30):");
        date = ErrorApp.dateHandlerBefore();

        System.out.println("Select timeslot:");
        counter = 0;
        for (String timeString : timeslots) {
            counter++;
            System.out.println(counter + ". " + timeString);
        }
        time = LocalTime.parse(timeslots[ErrorApp.safeTimeSlot() - 1]);
        System.out.println("Enter pax:");
        pax = ErrorApp.safeInteger();

        // if there is table not reserved for this time
        table = checkTableAvailability(date, time, pax);
        if (table != -1) {
            System.out.println("Enter name:");
            name = ErrorApp.alphaString();
            System.out.println("Enter contact:");
            int contact = ErrorApp.safeInteger();
            createReservation(table, date, time, pax, name, contact);
            ;
        } else {
            System.out.println("Full reservation. No table available.");
        }
        return;
    }

    /**
     * Driver UI code for check table availability
     */
    public void checkTableAvailability() {

        LocalDate date;
        LocalTime time;
        int counter, pax, table;
        System.out.println("Input date (eg. 2021-07-30):");
        date = ErrorApp.dateHandlerBefore();
        System.out.println("Select timeslot:");
        counter = 0;
        for (String timeString : timeslots) {
            counter++;
            System.out.println(counter + ". " + timeString);
        }
        time = LocalTime.parse(timeslots[ErrorApp.safeTimeSlot() - 1]);
        System.out.println("Enter pax:");
        pax = ErrorApp.safeInteger();
        table = checkTableAvailability(date, time, pax);
        if (table == -1)
            System.out.println("No table available.");
        else
            System.out.println("Table " + table + " is available.");

    }

    /**
     * Returns table number of Table that is available and not reserved given the
     * date, time and pax.
     * 
     * @param date Date of reservation .
     * @param time Time of reservation.
     * @param pax  Number of people.
     */
    public static int checkTableAvailability(LocalDate date, LocalTime time, int pax) {
        try {
            for (Table table : tables) {
                int check = 0;
                if (table.getCapacity() >= pax && table.getAvailability() == true) {
                    for (Reservation reservation : table.getReservations()) {
                        // table is available if there is no reservation for the current timeslot
                        // and the next timeslot
                        String temp = String.valueOf(time.getHour()) + ":00:00";
                        if (time.getHour() <= 9)
                            temp = "0" + String.valueOf(temp); // for 8am and 9am that dont have the zero in front, this
                                                               // is dumb but will work for now
                        LocalTime thisSlot = LocalTime.parse(temp);

                        temp = String.valueOf(time.getHour() + 1) + ":00:00";
                        if (time.getHour() <= 9)
                            temp = "0" + String.valueOf(temp);
                        LocalTime nextSlot = LocalTime.parse(temp);

                        if (reservation.getDate().isEqual(date) && (reservation.getTime().equals(thisSlot))
                                || reservation.getTime().equals(nextSlot)) {
                            check = -1;
                            break;
                        }
                    }
                    if (check == 0)
                        return table.getTableNumber();
                }
            }
        } catch (NoSuchElementException e) {
            return -1;
        }
        return -1;
    }

    /**
     * Returns the Table object.
     * 
     * @param table_number Table number to be found.
     * @return Table object or null if no table is found.
     */
    public Table getTable(int table_number) {
        for (Table table : tables) {
            if (table.getTableNumber() == table_number)
                return table;
        }
        return null;
    }

    /**
     * Adds a table to the list of tables.
     * 
     * @param table_number Table number of the new table.
     * @param capacity     Capacity of the new table.
     */
    public void addTable() {
        System.out.println("Enter table number:");
        int table_number = ErrorApp.safeInteger();
        System.out.println("Enter table capacity:");
        int capacity = ErrorApp.tableInt();
        System.out.println("Table has been added.");
        tables.add(new Table(table_number, capacity));
        System.out.println("Table added!");
    }

    /**
     * Find Table and set available to false
     * 
     * @param table_number
     */
    public static void setTableStatus(int table_number, boolean availability) {
        for (Table table : tables) {
            int cur_table_number = table.getTableNumber();
            if (cur_table_number == table_number) {
                table.setAvailable(availability);
            }
        }
    }

    /**
     * Prints all reservations in the system.
     */
    public void printAllReservations() {
        for (Table table : tables) {
            for (Reservation reservation : table.getReservations()) {
                System.out.println("Name: " + reservation.getName() + " Contact: " + reservation.getContact());
                System.out.println("Date: " + reservation.getDate() + " Time: " + reservation.getTime());
                System.out.println("Assigned to Table: " + reservation.getTableNumber());
            }
        }
    }
}