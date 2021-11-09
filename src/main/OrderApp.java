package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.Order.Order;
import main.Reservation.Reservation;
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
		//REFACTOR ifnull?
		if (orders == null) {
			System.out.println("No orders active. Returning...");
			return;
		}
		System.out.println("Select order to modify (-1 to go back): ");
		int i = 1;
		for (Order order : orders) {
			System.out.printf(i++ + ". ");
			order.printOrderShort();
		}
		Order activeOrder = null;
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do {
			choice = sc.nextInt();
			sc.nextLine(); //throw away the \n not consumed by nextInt()
	        if (0 <= choice && choice <= i-1) {
	        	activeOrder = orders.get(choice-1);
	        } else if (choice == -1) return;
	        else {
	        	System.out.println("Invalid selection. Try again?");
	        }
		} while (choice != -1 && choice >= i);
		System.out.println(	"(1) Add order items to order\n" +
							"(2) Remove order items from order");
		int choice1 = -1;
		do
		{
			choice1 = sc.nextInt();
			sc.nextLine(); //throw away the \n not consumed by nextInt()
			if (choice1 == 1)
			{
				activeOrder.addItem();
			}
			else if (choice1 == 2)
			{
				activeOrder.removeItem();
			}	
			else if (choice1 == -1) return;
		} while (choice1 != 1 && choice1 != 2 && choice1 != -1);
	}
	
}
