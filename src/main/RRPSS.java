package main;

import java.util.Scanner;

public class RRPSS {
	public static void main(String[] args) {
		MenuApp restaurantMenu = new MenuApp();
		ReservationApp reservation = new ReservationApp();
		StaffApp staff = new StaffApp();
		System.out.println("(1) Create/Update/Remove Menu Item\n" + "(2) Create/Update/Remove Promotion\n"
				+ "(3) Create Order\n" + "(4) View Order\n" + "(5) Add/Remove Order Item(s) to/from Order\n"
				+ "(6) Create Reservation Booking\n" + "(7) Check/Remove Reservation Booking\n"
				+ "(8) Check Table Availability\n" + "(9) Print Order Invoice\n" + "(10) Print Sale Revenue Report\n"
				+ "(11) Add in staff details\n" + "(12) Add tables\n" + "(-1) Exit");

		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println("Enter your Selection: ");
			choice = sc.nextInt();
			sc.nextLine();	
			switch(choice)
			{
				case 1:
					restaurantMenu.getFunctions();
					break;
				case 6:
					reservation.createReservation();
					break;
				case 7:
					reservation.checkRemoveReservations();
					break;
				case 8:
					reservation.checkTableAvailability();
					break;
				case 11:
					staff.createStaff();
					break;
				case 12:
					reservation.addTable();
					break;
				default:
					break;
			}
		} while (choice != -1);
	}
}
