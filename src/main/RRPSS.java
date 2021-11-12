package main;

import java.util.Scanner;

public class RRPSS {
	public static void main(String[] args) {
		MenuApp menuApp = new MenuApp();
		ReservationApp reservationApp = new ReservationApp();
		OrderApp orderApp = new OrderApp();
		OrderInvoiceApp invoiceApp = new OrderInvoiceApp();
		StaffApp staffApp = new StaffApp();
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println("(1) Edit Menu Item Catalog\n" + "(2) Edit Promotion Catalog\n" + "(3) Edit Menus\n"
					+ "(4) Create Order\n" + "(5) View Order\n" + "(6) Add/Remove Order Item(s) to/from Order\n"
					+ "(7) Create Reservation Booking\n" + "(8) Check/Remove Reservation Booking\n"
					+ "(9) Check Table Availability\n" + "(10) Print Order Invoice\n"
					+ "(11) Print Sale Revenue Report\n" + "(12) Add in staff details\n" + "(13) Add tables\n"
					+ "(14) Print all reservations\n" + "(-1) Exit");
			System.out.println("Enter your Selection: ");
			choice = ErrorApp.safeInteger();
			switch (choice) {
			case 1:
				menuApp.getNormalCatalogFunctions();
				break;
			case 2:
				menuApp.getPromoCatalogFunctions();
				break;
			case 3:
				menuApp.getMenuFunctions();
				break;
			case 4:
				orderApp.createOrder();
				break;
			case 5:
				orderApp.printDetailedOrders();
				break;
			case 6:
				orderApp.modifyOrders();
				break;
			case 7:
				reservationApp.createReservation();
				break;
			case 8:
				reservationApp.checkRemoveReservations();
				break;
			case 9:
				reservationApp.checkTableAvailability();
				break;
			case 10:
				invoiceApp.printInvoice();
				break;
			case 12:
				staffApp.createStaff();
				break;
			case 13:
				reservationApp.addTable();
				break;
			case 14:
				reservationApp.printAllReservations();
				break;
			default:
				break;
			}
		} while (choice != -1);
	}
}
