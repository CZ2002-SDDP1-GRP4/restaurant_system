package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.Order.Order;
import main.Reservation.Reservation;
import main.Reservation.Table;
import main.Staff.Staff;

public class OrderApp {
	private ArrayList<Order> orders;
	
	public OrderApp() {
		orders = new ArrayList<Order>();
	}

	public void createOrder() {
		int staffId = StaffApp.getStaffbyId();
		if (staffId == -1) return;
		Staff staff = StaffApp.getStaffbyId(staffId);

		int table_number = -1;
		
		String name = null;
		
		// REFACTOR ErrorApp?
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do
		{
			System.out.println(	"Does this customer have a reservation? (-1 to go back)\n" +
								"(1) Yes\n" +
								"(2) No (a walk-in customer)"
			);
			choice = sc.nextInt();
			sc.nextLine(); //throw away the \n not consumed by nextInt()
			
			
			if (choice == 1)
			{
				System.out.println("What's the customer's name used in the reservation?");
				name = sc.nextLine();
				LocalDate today = LocalDate.now();
                LocalTime now = LocalTime.now();
				Reservation reservation = ReservationApp.checkReservation(name, today, now);
				if (reservation != null){
					table_number = reservation.getTableNumber();
                }else{
                    System.out.println("No reservation by this customer found for this date & time.");
                    return;
                }
			}
			else if (choice == 2)
			{
				System.out.println("Number of pax?");
				int pax = sc.nextInt();
				sc.nextLine(); //throw away the \n not consumed by nextInt()
				LocalDate today = LocalDate.now();
                LocalTime now = LocalTime.now();
                table_number = ReservationApp.checkTableAvailability(today, now, pax);
                if (table_number == -1) {
                	System.out.println("No table available.");
                	return;
                }
                else {
                	System.out.println("Table available.");
                }
			}
			else if (choice == -1)
			{
				return;
			}
			else 
			{
				System.out.println("Invalid selection. Try again?");
			}
		} while (choice != 1 && choice != 2 && choice != -1);
		
        System.out.println("Customer has been assigned table " + table_number);
        
        //REFACTOR compare ReservationApp 189 and StaffApp 61
        orders.add(new Order(table_number, staff));
        ReservationApp.setOccupied(table_number);
	}
	
	public void viewOrder() {
		for (Order order : orders) { 
			order.printOrder();
		}
	}
	
	public void modifyOrder() {
		
	}
	
	
}
