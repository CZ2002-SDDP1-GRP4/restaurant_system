package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import main.Reservation.Reservation;
import main.Reservation.Table;
/**
 * Represents the app class for interfacing with reservation and table functions.
 * @author Brendan Ang
 * @version 1.0
 */
public class ReservationApp {
    /**The list of tables which the restaurant has.*/
    private ArrayList<Table> tables;
    /**Expiry time constant in minutes */
    private static final int expiryTime = 10;
    /**Start time of available reservation timeslots.*/
    private String [] timeslots = {
        "08:00",
        "09:00",
        "10:00",
        "11:00",
        "12:00",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
        "19:00",
    };

    /**Create new reservation app with list of tables.*/
    public ReservationApp(){
        tables = new ArrayList<Table>();
    }

    /**
     * Driver UI code for check and remove reservations
     */
    public void checkRemoveReservations(){

        //remove expired reservations
        removeExpiredReservations();
        Scanner sc = new Scanner(System.in);
        System.out.println(
            "1. Check Reservation \n"+
            "2. Remove Reservation\n"+
            "Enter selection:"
        );
        int choice = sc.nextInt();
        sc.nextLine(); 

        LocalDate date; LocalTime time; int counter; String name;String temp;
        switch(choice){
            case 1:
                System.out.println("Enter name:");
                name = sc.nextLine();
                 
                System.out.println("Input date (eg. 2021-07-30):");
                temp = sc.nextLine();
                if (ErrorApp.dateHandler(temp) == true){ 
                    date = LocalDate.parse(temp);
                } else {
                    return;
                }

                System.out.println("Select timeslot:");
                counter = 0;
                for (String timeString : timeslots) {
                    counter++;
                    System.out.println(counter + ". " + timeString);
                }
                time = LocalTime.parse(timeslots[sc.nextInt()-1]);
                
                Reservation reserved = checkReservation(name, date, time);
                if (reserved != null){
                    System.out.println(
                        "Reservation found:\n"+
                        "Name:"+reserved.getName()+"\n"+
                        "Table Number:" + reserved.getTableNumber()
                    );
                }else{
                    System.out.println("No reservation found for this date & time.");
                }
                break;
            
            case 2:
                System.out.println("Enter name:");
                name = sc.nextLine();
                System.out.println("Input date (eg. 2021-07-30):");
                temp = sc.nextLine();
                if (ErrorApp.dateHandler(temp) == true){ 
                    date = LocalDate.parse(temp);
                } else {
                    return;
                }
                System.out.println("Select timeslot:");
                counter = 0;
                for (String timeString : timeslots) {
                    counter++;
                    System.out.println(counter + ". " + timeString);
                }
                time = LocalTime.parse(timeslots[sc.nextInt()-1]);
                removeReservation(checkReservation(name, date, time));
                break;
            default:
                break;
        }
    }

    /**Returns the Reservation object if it exists. 
     * @param name Name of reserving person. 
     * @param date Date of reservation. 
     * @param time Time of reservation. 
     * @return Reservation object or null.
     * */
    public Reservation checkReservation(String name, LocalDate date, LocalTime time){
        for (Table table : tables) {
            for (Reservation reservation : table.getReservations()) {
                if (reservation.getName().equals(name) && reservation.getDate().isEqual(date) && reservation.getTime().equals(time)){
                    return reservation;
                }
            }
        }
        return null;
    }

    /**
     * Remove reservations which have expired.
     */
    public void removeExpiredReservations(){

        for (Table table : tables) {
            List <Reservation> found = new ArrayList<Reservation>();
            for (Reservation reservation : table.getReservations()) {
                if (reservation.getDate().isBefore(LocalDate.now())){
                    found.add(reservation);
                }else if (reservation.getDate().isEqual(LocalDate.now()) && reservation.getTime().isBefore(LocalTime.now().plusMinutes(expiryTime))){
                    found.add(reservation);
                }
            }
            table.getReservations().removeAll(found);
        }
    }

    /**
     * Removes the Reservation object from a table
     * @param reservation Reservation object to be removed.
     */
    public void removeReservation(Reservation reservation){
        if (reservation == null){
            System.out.println("Nothing to remove");
        }else{
            int table_number = reservation.getTableNumber();
            for (Table table : tables) {
                if (table.getTableNumber() == table_number){
                    table.getReservations().remove(reservation);
                    System.out.println("Reservation removed!");
                }
            }
        }
    }

    /**
     * Creates a reservation
     * @param table_number Table number
     * @param date Date of reservation.
     * @param time Time of reservation.
     * @param pax Number of people.
     * @param name Name of reserving person.
     * @param contact Contact of reserving person.
     */
    public void createReservation(int table_number, LocalDate date, LocalTime time, int pax, String name, int contact){
        Table tb = getTable(table_number);
        tb.getReservations().add(new Reservation(table_number, date, time, pax, name, contact));
        System.out.println("Reservation created!");
    }

    public void createReservation(){

        Scanner sc = new Scanner(System.in);
        LocalDate date; LocalTime time; int counter, pax, table; String name;String temp; 
        System.out.println("Input date (eg. 2021-07-30):");
        temp = sc.nextLine();
        if (ErrorApp.dateHandler(temp) == true){ 
            date = LocalDate.parse(temp);
        } else {
            return;
        }

        //date must be after current date
        if (date.isBefore(LocalDate.now())){
            System.out.println("Invalid date");
            return;
        }

        System.out.println("Select timeslot:");
        counter = 0;
        for (String timeString : timeslots) {
            counter++;
            System.out.println(counter + ". " + timeString);
        }
        time = LocalTime.parse(timeslots[sc.nextInt()-1]);
        sc.nextLine(); //throw away the \n not consumed by nextInt()
        System.out.println("Enter pax:");
        pax = sc.nextInt();
        sc.nextLine(); //throw away the \n not consumed by nextInt()

        //if there is table not reserved for this time
        table = checkTableAvailability(date, time, pax);
        if (table != -1){
            System.out.println("Enter name:");
            name = sc.nextLine();
            System.out.println("Enter contact:");
            int contact = sc.nextInt();
            sc.nextLine(); //throw away the \n not consumed by nextInt()
            createReservation(table, date, time, pax, name, contact);;
        }else{
            System.out.println("Full reservation. No table available.");
        }
        return;
    }

    /**
     * Driver UI code for check table availability
     */
    public void checkTableAvailability(){

        Scanner sc = new Scanner(System.in);
        LocalDate date; LocalTime time; int counter, pax, table; String temp; 
        System.out.println("Input date (eg. 2021-07-30):");
        temp = sc.nextLine();
        if (ErrorApp.dateHandler(temp) == true){ 
            date = LocalDate.parse(temp);
        } else {
            return;
        }
        System.out.println("Select timeslot:");
        counter = 0;
        for (String timeString : timeslots) {
            counter++;
            System.out.println(counter + ". " + timeString);
        }
        time = LocalTime.parse(timeslots[sc.nextInt()-1]);
        System.out.println("Enter pax:");
        pax = sc.nextInt();
        table = checkTableAvailability(date, time, pax);
        if (table == -1) System.out.println("No table available.");
        else System.out.println("Table " + table + " is available.");

    }

    /**Returns table number of Table that is available and not reserved given the date, time and pax. 
     * @param date Date of reservation .
     * @param time Time of reservation. 
     * @param pax Number of people.
    */
    public int checkTableAvailability(LocalDate date, LocalTime time, int pax){
        try{
            for (Table table : tables) {
                int check = 0;
                if (table.getCapacity() >= pax && table.getAvailability() == true){
                    for (Reservation reservation : table.getReservations()) {
                        if (reservation.getDate().isEqual(date) && reservation.getTime().equals(time)){
                            check = -1;
                            break;
                        }
                    }
                    if (check == 0) return table.getTableNumber();
                }
            }
        }catch(NoSuchElementException e){
            return -1;
        }
        return -1;
    }

    /**
     * Returns the Table object.
     * @param table_number Table number to be found.
     * @return Table object or null if no table is found.
     */
    public Table getTable(int table_number){
        for (Table table : tables) {
            if (table.getTableNumber() == table_number) return table;
        }
        return null;
    }
    
    /**
     * Adds a table to the list of tables.
     * @param table_number Table number of the new table.
     * @param capacity Capacity of the new table.
     */
    public void addTable(){
        System.out.println("Enter table number:");
        Scanner sc = new Scanner(System.in);
        int table_number = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter table capacity:");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.println("Table has been added.");
        tables.add(new Table(table_number, capacity));
        System.out.println("Table added!");
    }
    
    //test function to view reservations
    public void viewReservation(int table_number){
        for (Table table : tables) {
            if (table.getTableNumber() == table_number){
                for (Reservation reservation : table.getReservations()) {
                    System.out.println(reservation.getName());
                }
            }
        }
    }
}