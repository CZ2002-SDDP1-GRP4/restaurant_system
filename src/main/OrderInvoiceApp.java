package main;

import java.time.format.DateTimeFormatter;

import main.Menu.MenuItem;
import main.Order.Order;
import main.Order.OrderInvoice;

/**
 * Represents the Order Invoice App class that is responsible for
 * 1) Processing a single OrderInvoice
 * 2) GROUPING THE SALE ITEMS WITH QUANTITY 
 * 3) Printing it in the Order Invoice format.
 * It is a concrete class that inherits from the AggregatePrint abstract class.
 * @author Bryan
 * @version 1.0
 */
public class OrderInvoiceApp extends AggregatePrint {
	/**
	 * An attribute storing the Order Invoice object.
	 */
	private static OrderInvoice orderInvoice;
	
	/**
	 * Method to process an Order Invoice. This is done by
	 * 1) Printing the list of orders for the user to choose
	 * 2) Retreiving the selection
	 * 3) Calling the appropriate method in Order Invoice class to process it
	 * @Override
	 */
	protected void process() {
		OrderApp.printShortOrders();
		System.out.println("Select order to generate invoice (-1 to go back):");
		int choice = ErrorApp.safeInteger();
		if (choice == -1)
		{
			return;
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
				print();
				OrderApp.remove(choice-1);
			}
			else if (success == 2)
			{
				System.out.println("Nothing was ordered. Table will be cleared.");
				OrderApp.remove(choice-1);
			}
			else {
				System.out.println("Processing cancelled.");
				return;
			}
		}
		else {
			System.out.println("Invalid selection.");
			return;
		}
	}

	/**
	 * Method to print the Order Invoice. 
	 * The Order Invoice has been specially and carefully formatted to 
	 */
	@Override
	public void print() {
		if (orderInvoice == null) {
			System.out.println("Returning...");
			return;
		}
		groupSaleItems(orderInvoice.getOrderItems());
		System.out.println("Generating invoice...");
		System.out.println(
				  "\n" + "=========== ORDER INVOICE ==========="
		+ "\n" + "Date: "  + orderInvoice.getInvoiceDate()
		+ "\n" + "Time: "  + orderInvoice.getInvoiceTime().format(DateTimeFormatter.ofPattern("HH:mm"))
		+ "\n" + "Table: " + orderInvoice.getTable()
		+ "\n" + String.format("%3s %-26s %6s\n", "Qty", "Item", "Price")
			   + String.format("%3s %-26s %6s", "---", "----", "-----")
		);
		
		int i = 0;
		for (MenuItem orderItem : grpedSaleItems)
		{
			String s = orderItem.getName();
			if (s.length() > 26)
			{
				s = s.substring(0,26);
			}
			System.out.println(String.format("%3s %-27s %5s\n", grpedSaleQty[i], s, orderItem.getPrice()*grpedSaleQty[i]));
			i++;
		}
								
				
		System.out.println("-------------------------------------");
		System.out.println(String.format("Subtotal" 		+ "%29.2f", (orderInvoice.getInitialPrice())));
		if ((orderInvoice.getInitialPrice() != orderInvoice.getDiscounted()))
		{
			System.out.println(String.format("Discount" 		+ "%29.2f", (orderInvoice.getInitialPrice()-orderInvoice.getDiscounted())));
		}
		System.out.println(String.format("Service Charge" 	+ "%23.2f", (orderInvoice.getRevenue()-orderInvoice.getDiscounted())));
		System.out.println(String.format("GST"			 	+ "%34.2f\n", (orderInvoice.getFinalPrice()-orderInvoice.getRevenue())));
		System.out.println(String.format("FINAL PRICE" 		+ "%26.2f\n", (orderInvoice.getFinalPrice())));
			
		System.out.println(
		         "============= THANK YOU ============="
		+ "\n" + "========= HAVE A GREAT DAY! ========="
	    + "\n"
		);
		
		return;
	}
}
