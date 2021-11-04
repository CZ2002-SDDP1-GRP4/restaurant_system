package main;

import java.util.Scanner;

public class RRPSS {
	public static void main(String[] args) {
		MenuApp menu = new MenuApp();
		ReservationApp reservation = new ReservationApp();
		StaffApp staff = new StaffApp();
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println("(1) Edit Menu Item Catalog\n" + "(2) Edit Promotion Catalog\n" + "(3) Edit Menus\n"
					+ "(4) Create Order\n" + "(5) View Order\n" + "(6) Add/Remove Order Item(s) to/from Order\n"
					+ "(7) Create Reservation Booking\n" + "(8) Check/Remove Reservation Booking\n"
					+ "(9) Check Table Availability\n" + "(10) Print Order Invoice\n"
					+ "(11) Print Sale Revenue Report\n" + "(12) Add in staff details\n" + "(13) Add tables\n"
					+ "(-1) Exit");
			System.out.println("Enter your Selection: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				menu.getNormalCatalogFunctions();
				break;
			case 2:
				menu.getPromoCatalogFunctions();
				break;
			case 3:
				menu.getMenuFunctions();
				break;
			case 7:
				reservation.createReservation();
				break;
			case 8:
				reservation.checkRemoveReservations();
				break;
			case 9:
				reservation.checkTableAvailability();
				break;
			case 12:
				staff.createStaff();
				break;
			case 13:
				reservation.addTable();
				break;
			default:
				break;
			}
		} while (choice != -1);
	}
}
