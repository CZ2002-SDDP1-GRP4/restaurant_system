package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Order.Order;
import main.Order.OrderInvoice;

public class OrderInvoiceApp {
	private static ArrayList<OrderInvoice> orderInvoices = new ArrayList<OrderInvoice>();

	public void printInvoice() {
		OrderApp.printShortOrders();
		int choice;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select order to generate invoice (-1 to go back):");
		choice = ErrorApp.safeInteger();
		if (choice == -1)
		{
			return;
		}
		else if (1<=choice && choice<=OrderApp.getOrdersSize())
		{
			Order orderToProcess = OrderApp.get(choice-1);
			OrderInvoice orderinvoice = new OrderInvoice(orderToProcess);
			orderInvoices.add(orderinvoice.processInvoice());
			OrderApp.remove(choice-1);
		}
		else {
			System.out.println("Invalid selection.");
			return;
		}
		
		OrderInvoice printMe = orderInvoices.get(orderInvoices.size()-1);
		System.out.println(
					"ORDER INVOICE:\n" +
					"Date: " + printMe.getInvoiceDate() + "  " + "Time: " + printMe.getInvoiceTime() + "\n"
		);
		printMe.printInvoiceOrders();
		System.out.println("Total price: " + printMe.getTotalPrice());
		double discount = printMe.getTotalPrice()-printMe.getFinalPrice();
		if (discount != 0)
		{
			System.out.println("Discount applied: -" + discount);
			System.out.println("Final price: " + printMe.getFinalPrice());
		}
	}
	
	

}
