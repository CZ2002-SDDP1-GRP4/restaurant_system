package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.Order.Order;
import main.Reservation.Reservation;
import main.Staff.Staff;
/**
 * Represents the Order App class that stores all active orders and 
 * provides methods to interface with all orders
 * @author Bryan
 * @version 1.0
 */
public class OrderApp {
	/**
	 * The array list of active orders the restaurant is currently serving
	 */
	private static ArrayList<Order> orders;
	
	/**
	 * Public constructor instantiating the order array list.
	 */
	public OrderApp() {
		orders = new ArrayList<Order>();
	}

	/**
	 * Method to add a new order to the order array list, after doing the necessary checks 
	 * to gather relevant information
	 * {@link StaffApp#getStaffbyId()}
	 * {@link StaffApp#getStaffbyId(staffId)}
	 * {@link ReservationApp#checkReservation()}
	 * {@link Reservation#getTableNumber()}
	 * {@link ReservationApp#checkTableAvailability()}
	 * {@link Order#Order()}
	 * {@link ReservationApp#setTableStatus()}
	 */
	public void createOrder() {
		int staffId = StaffApp.getStaffbyId();
		if (staffId == -1) return;
		Staff staff = StaffApp.getStaffbyId(staffId);

		int table_number = -1;
		
		String name = null;
		int choice = 0;
		do
		{
			System.out.println(	"Does this customer have a reservation? (-1 to go back)\n" +
								"(1) Yes\n" +
								"(2) No (a walk-in customer)"
			);
			choice = ErrorApp.safeInteger();
			
			if (choice == 1)
			{
				System.out.println("What's the customer's name used in the reservation?");
				name = ErrorApp.alphaString();
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
				int pax = ErrorApp.safeInteger();
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
        
        orders.add(new Order(table_number, staff));
        ReservationApp.setTableStatus(table_number, false);
	}
	
	/**
	 * Method to print a detailed list of orders.
	 */
	public void printDetailedOrders() {
		if (orders.size() == 0 || orders == null)
		{
			System.out.println("No active orders.");
		}
		for (Order order : orders) { 
			order.printDetailedOrder();
		}
	}
	
	/**
	 * Method to print a summarised list of orders.
	 * Instantiated as static to allow OrderInvoice to call this method without needing to 
	 * instantiate an instance object of this OrderApp class
	 */
	public static void printShortOrders() {
		for (Order order : orders) { 
			order.printShortOrder();
		}
	}
	
	/**
	 * Method to choose an order from the list to modify.
	 */
	public void modifyOrders() {
		if (orders == null || orders.size() == 0) {
			System.out.println("No orders active. Returning...");
			return;
		}
		System.out.println("Select order to modify (-1 to go back): ");
		int i = 1;
		for (Order order : orders) {
			System.out.printf(i++ + ". ");
			order.printShortOrder();
		}
		Order activeOrder = null;
		int choice = -1;
		do {
			choice = ErrorApp.safeInteger();
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
			choice1 = ErrorApp.safeInteger();
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
	
	/**
	 * Getter method to return the size of the orders array list.
	 * @return Integer value of the number of active orders.
	 */
	public static int getOrdersSize() {
		return orders.size();
	}

	/**
	 * Getter method to find an order using its index.
	 * @param Integer value of order's index
	 * @return Order object
	 */
	public static Order get(int choice) {
		return orders.get(choice);
	}
	
	/**
	 * Setter method to mark an order as done. This sets the table as available again, 
	 * and removes the order from the list.
	 * @param Integer value of order's index
	 */
	public static void remove(int choice) {
		ReservationApp.setTableStatus(orders.get(choice).getOrderTable(), true);
		orders.remove(choice);
	}
}
