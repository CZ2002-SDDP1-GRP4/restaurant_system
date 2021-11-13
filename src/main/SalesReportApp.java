package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.Menu.MenuItem;
import main.Order.OrderInvoice;
import main.IO.*;

/**
 * Represents the Sales Report App class that is responsible for 1) Receiving
 * the order invoices 2) Allowing the user to choose the time period for the
 * reservation 3) GROUPING THE SALE ITEMS WITH QUANTITY 4) Printing it in the
 * Order Invoice format. It is a concrete class that inherits from the
 * AggregatePrint abstract class.
 * 
 * @author Bryan
 * @version 1.0
 */
public class SalesReportApp extends AggregatePrint implements RW {
	/**
	 * An attribute remembering the Order Invoice object.
	 */
	private static ArrayList<OrderInvoice> orderInvoices;
	/**
	 * An attribute to calculate total revenue
	 */
	private double totalRevenue;
	private final String filename = "orderinvoices";

	/**
	 * Public constructor to create sales report app and initailise revenue to 0
	 */
	public SalesReportApp() {
		orderInvoices = new ArrayList<OrderInvoice>();
		grpedSaleItems = new ArrayList<MenuItem>();
		totalRevenue = 0;
	}

	/**
	 * Method to add an order invoice to the report
	 * 
	 * @param OrderInvoice object orderInvoice
	 */
	public static void addToReport(OrderInvoice orderInvoice) {
		orderInvoices.add(orderInvoice);
	}

	/**
	 * Method to process a Sales Report. This is done by 1) Prompting the user which
	 * time period they want 2) Calling the appropriate overloaded processReport
	 * method
	 * 
	 * @Override
	 */
	public void process() {
		System.out.println("By (1) Day (2) Month");
		int choice = ErrorApp.safeInteger();
		if (choice == 1) {
			System.out.println("Input date (eg. 2021-07-30):");
			LocalDate date = ErrorApp.dateHandlerAfter();
			processReport(date);
		} else if (choice == 2) {
			System.out.println("Select month: (-1 to return)\n" + "(1) January\n" + "(2) Feburary\n" + "(3) March\n"
					+ "(4) April\n" + "(5) May\n" + "(6) June\n" + "(7) July\n" + "(8) August\n" + "(9) September\n"
					+ "(10) October\n" + "(11) November\n" + "(12) December");
			int month = ErrorApp.safeInteger();
			if (month == -1)
				return;
			else if (1 > month || month > 12) {
				System.out.println("Invalid choice");
			} else
				processReport(month);
		} else if (choice == -1)
			return;
	}

	/**
	 * Overloaded method to process a Sales Report. It checks whether the menuitem
	 * is within the time period.
	 * 
	 * @Override
	 */
	public void processReport(int month) {
		ArrayList<MenuItem> ungroupedSaleItems = new ArrayList<MenuItem>();
		totalRevenue = 0;
		for (OrderInvoice orderinvoice : orderInvoices) {
			if (month == orderinvoice.getInvoiceDate().getMonthValue()) {
				ungroupedSaleItems.addAll(orderinvoice.getOrderItems());
				totalRevenue += orderinvoice.getRevenue();
			}
		}
		groupSaleItems(ungroupedSaleItems);
		System.out.println("Sales Revenue Report (for " + Month.of(month) + ")");
		print();
	}

	/**
	 * Overloaded method to process a Sales Report. It checks whether the menuitem
	 * is within the time period.
	 * 
	 * @Override
	 */
	public void processReport(LocalDate date) {
		totalRevenue = 0;
		ArrayList<MenuItem> ungroupedSaleItems = new ArrayList<MenuItem>();
		for (OrderInvoice orderinvoice : orderInvoices) {
			if (date.isEqual(orderinvoice.getInvoiceDate())) {
				ungroupedSaleItems.addAll(orderinvoice.getOrderItems());
				totalRevenue += orderinvoice.getRevenue();
			}
		}
		groupSaleItems(ungroupedSaleItems);
		System.out.println("Sales Revenue Report (for " + date + ")");
		print();
	}

	/**
	 * Mthod to print a Sales Report in the proper format
	 * 
	 * @Override
	 */
	public void print() {
		if (totalRevenue == 0)
		{
			System.out.println("No sales during this period");
			return;
		}
		System.out.printf("Total revenue is $%.2f\n", totalRevenue);
		System.out.println(
				"\n" + String.format("%-26s %6s", "Item", "Qty") + "\n" + String.format("%-26s %6s", "----", "---"));
		int i = 0;
		for (MenuItem saleItem : grpedSaleItems) {
			System.out.println(String.format("%-27s %5s\n", saleItem.getName(), grpedSaleQty[i++]));
		}
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		IO.setFileName(this.filename);
		IO.setWriter();

		for (int i = 0; i < orderInvoices.size(); i++) {
			OrderInvoice oi = orderInvoices.get(i);
			IO.write(oi.getInvoiceDate() + ",");
			IO.write(oi.getInvoiceTime() + ",");
			IO.write(oi.getTable() + ",");
			IO.write(oi.getInitialPrice() + ",");
			IO.write(oi.getDiscounted() + ",");
			IO.write(oi.getRevenue() + ",");
			IO.write(oi.getFinalPrice() + "\n");
			for (int j = 0; j < oi.getOrderItems().size(); j++) {
				MenuItem mi = oi.getOrderItems().get(j);
				IO.write(mi.getName() + "�");
				IO.write(mi.getPrice() + "�");
				IO.write(mi.getType() + "�");
				IO.write(mi.getDescription() + "\n");
			}
			IO.write("-----\n");
		}
		IO.closeWriter();

	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		IO.setFileName(filename);
		IO.setReader();

		if (IO.checkFileExist()) {
			System.out.println("Order Invoice backup file exists, restoring now.");
			IO.readLine(); // reads the first line of the text
			while (!IO.isEOL()) // as long as we are not at the end of line
			{
				String[] data = IO.getLine().split(","); // takes line and split by comma
				LocalDate date = LocalDate.parse(data[0], DateTimeFormatter.ofPattern("uuuu-M-d"));
				LocalTime time = LocalTime.parse(data[1]);
				int table = Integer.parseInt(data[2]);
				double initialPrice = Double.parseDouble(data[3]);
				double discounted = Double.parseDouble(data[4]);
				double revenue = Double.parseDouble(data[5]);
				double finalPrice = Double.parseDouble(data[6]);
				ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>();
				IO.readLine(); // get the next line
				while (!IO.isEOL() && !IO.getLine().equals("-----")) {
					String[] menuItemData = IO.getLine().split("�");
					String name = menuItemData[0];
					double price = Double.parseDouble(menuItemData[1]);
					String type = menuItemData[2];
					String description = menuItemData[3];
					MenuItem mi = new MenuItem(name, price, type, description);
					orderItems.add(mi);
					IO.readLine();
				}
				OrderInvoice oi = new OrderInvoice(date, time, table, initialPrice, discounted, revenue, finalPrice,
						orderItems);
				orderInvoices.add(oi);
				IO.readLine();
			}
		}

		IO.closeReader();

	}
}
