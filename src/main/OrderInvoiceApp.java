package main;

import java.time.format.DateTimeFormatter;

import main.Menu.MenuItem;
import main.Order.Order;
import main.Order.OrderInvoice;

public class OrderInvoiceApp {
	private static OrderInvoice orderInvoice;
	
	public OrderInvoice processInvoice() {
		OrderApp.printShortOrders();
		System.out.println("Select order to generate invoice (-1 to go back):");
		int choice = ErrorApp.safeInteger();
		if (choice == -1)
		{
			return null;
		}
		else if (1<=choice && choice<=OrderApp.getOrdersSize())
		{
			System.out.println("Processing order...");
			Order orderToProcess = OrderApp.get(choice-1);
			orderInvoice = new OrderInvoice(orderToProcess);
			int success = orderInvoice.processInvoice();
			if (success == 1)
			{
				SalesReportApp.addToReport(orderInvoice);
				OrderInvoiceApp.printInvoice();
				OrderApp.remove(choice-1);
			}
			else
			{
				System.out.println("Processing cancelled.");
				return null;
			}
			
		}
		else {
			System.out.println("Invalid selection.");
			return null;
		}
		return null;
	}
	
	
	public static void printInvoice() {
		if (orderInvoice == null) {
			System.out.println("Returning...");
			return;
		}
		System.out.println("Generating invoice...");
		System.out.println(
		  "\n" + "========= ORDER INVOICE ========="
		+ "\n" + "Date: "  + orderInvoice.getInvoiceDate()
		+ "\n" + "Time: "  + orderInvoice.getInvoiceTime().format(DateTimeFormatter.ofPattern("HH:mm"))
		+ "\n" + "Table: " + orderInvoice.getTable()
		+ "\n" + String.format("%-25s %7s\n", "Item", "Price")
		       + String.format("%-25s %7s\n", "----", "-----")
		);
		
		for (MenuItem orderItem : orderInvoice.getOrderItems())
		{
			String s = orderItem.getName();
			if (s.length() > 26)
			{
				s = s.substring(0,26);
			}
			System.out.println(String.format("%-26s" + "%7s\n", s, orderItem.getPrice()));
		}
								
				
		System.out.println("---------------------------------");
		System.out.println(String.format("Subtotal" 		+ "%25.2f", (orderInvoice.getInitialPrice())));
		if ((orderInvoice.getInitialPrice() != orderInvoice.getDiscounted()))
		{
			System.out.println(String.format("Discount" 		+ "%25.2f", (orderInvoice.getInitialPrice()-orderInvoice.getDiscounted())));
		}
		System.out.println(String.format("Service Charge" 	+ "%19.2f", (orderInvoice.getRevenue()-orderInvoice.getDiscounted())));
		System.out.println(String.format("GST"			 	+ "%30.2f\n", (orderInvoice.getFinalPrice()-orderInvoice.getRevenue())));
		System.out.println(String.format("FINAL PRICE" 		+ "%22.2f\n", (orderInvoice.getFinalPrice())));
			
		System.out.println(
		         "=========== THANK YOU ==========="
		+ "\n" + "======= HAVE A GREAT DAY! ======="
	    + "\n"
		);
		
		return;
	}
}
