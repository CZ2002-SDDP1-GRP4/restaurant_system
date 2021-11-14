package main;

import main.IO.IO;

/** The Restaurant Reservation and Point of Sale System
 * 
 * @author chuag
 *
 */
public class RRPSS {
	
	/** This is the main function class to our Restaurant Reservation and Point of Sale System
	 * 
	 * @author chuag
	 * @param args series of string input by user for testing test cases
	 */
	public static void main(String[] args) {
		System.out.println("Starting RRPSS ...");
		IO.start();

		DiscountApp discountApp = new DiscountApp();
		SalesReportApp reportApp = new SalesReportApp();

		MenuApp menuApp = new MenuApp();
		ReservationApp reservationApp = new ReservationApp();
		OrderApp orderApp = new OrderApp();
		OrderInvoiceApp invoiceApp = new OrderInvoiceApp();
		StaffApp staffApp = new StaffApp();

		System.out.println("Reading saved data... ");
		// loading data in from backup .txt files
		menuApp.read();
		reportApp.read();

		System.out.println("\n+--------------------+");
		System.out.println("|  Welcome to RRPSS  |");
		System.out.println("+--------------------+\n");

		int choice = -1;
		do {
			System.out.println("(1) Edit Menu Item Catalog\n" + "(2) Edit Promotion Catalog\n" + "(3) Edit Menus\n"
					+ "(4) Create Order\n" + "(5) View Order\n" + "(6) Add/Remove Order Item(s) to/from Order\n"
					+ "(7) Create Reservation Booking\n" + "(8) Check/Remove Reservation Booking\n"
					+ "(9) Check Table Availability\n" + "(10) Print Order Invoice\n"
					+ "(11) Print Sales Revenue Report\n" + "(12) Add in Staff Details\n" + "(13) Add Tables\n"
					+ "(14) Add Discount\n" + "(15) Print All Reservations\n" + "(-1) Exit");

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
				invoiceApp.process();
				break;
			case 11:
				reportApp.process();
				break;
			case 12:
				staffApp.createStaff();
				break;
			case 13:
				reservationApp.addTable();
				break;
			case 14:
				discountApp.createDiscount();
				break;
			case 15:
				reservationApp.printAllReservations();
				break;
			default:
				break;
			}
		} while (choice != -1);
		// backing up
		System.out.println("Saving system data...");
		menuApp.write();
		reportApp.write();
		System.out.println("Thank you for using RRPSS!\n[Program closed]");
	}
}
