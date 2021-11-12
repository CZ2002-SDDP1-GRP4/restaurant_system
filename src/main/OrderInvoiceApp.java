package main;

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
		System.out.println(
		  "\n" + "====== ORDER INVOICE ======"
		+ "\n" + "Date: "  + orderInvoice.getInvoiceDate()
		+ "\n" + "Time: "  + orderInvoice.getInvoiceTime()
		+ "\n" + "Table: " + orderInvoice.getTable()
		+ "\n" + "Orders: (Item ---- Price)"
		);
		
		for (MenuItem orderItem : orderInvoice.getOrderItems())
		{

			System.out.printf("%s ----- %.2f\n", orderItem.getName(), orderItem.getPrice());
		}
		
		System.out.printf(                  
				 "---------------------------"
		+ "\n" + "Subtotal:  	$%.2f", (orderInvoice.getInitialPrice())
		+ "\n" + "Discount:    -$%.2f", (orderInvoice.getInitialPrice()-orderInvoice.getDiscounted())
		+ "\n" + "Serv Charge: +$%.2f", (orderInvoice.getRevenue()-orderInvoice.getDiscounted())
		+ "\n" + "FINAL PRICE:	$%.2f", (orderInvoice.getFinalPrice())
		);
		
		System.out.println(
				 "======== THANK YOU ========"
		+ "\n" + "==== HAVE A GREAT DAY! ===="
	    + "\n"
		);
		
	}
}
