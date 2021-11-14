package main.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import main.DiscountApp;
import main.ErrorApp;
import main.Discount.Discount;
import main.Menu.MenuItem;

/**
 * 
 * The order invoice class, this pertains to a particular record/transaction
 *
 */
public class OrderInvoice {
	private LocalDate date;
	private LocalTime time;
	private int table;
	private ArrayList<MenuItem> orderItems;
	private double initialPrice;
	private double discounted;
	private double revenue;
	private double finalPrice;
	
	
	/**
	 * A general order invoice constructor class
	 * this is used by IO to create order invoice objects on back up
	 * @param date saved date
	 * @param time saved time
	 * @param table saved table
	 * @param initialPrice saved initial price
	 * @param discounted saved discounted value
	 * @param revenue saved revenue
	 * @param finalPrice saved final price
	 * @param orderItems saved items in order
	 */
	public OrderInvoice(LocalDate date, LocalTime time, int table, double initialPrice, double discounted, double revenue, double finalPrice, ArrayList<MenuItem> orderItems) {
		this.date = date;
		this.time = time;
		this.table = table;
		this.initialPrice = initialPrice;
		this.discounted = discounted;
		this.revenue = revenue;
		this.finalPrice = finalPrice;
		this.orderItems = orderItems;
	}
	
	/**
	 * 
	 * @param OrderToProcess The order object to be proceesed into an invoice
	 */
	public OrderInvoice(Order OrderToProcess) {
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.table = OrderToProcess.getOrderTable();
		this.orderItems = OrderToProcess.getOrderItems();
	}
	
	/**
	 * Method to process an invoice
	 * @return the outcome state of processing the invoice.
	 * There are 4 states: -1, 0, 1, 2
	 * -1: invalid return value after applying discount
	 * 0: invalid user input
	 * 1: success
	 * 2: success but the total value of the order is $0
	 */
	public int processInvoice() {
		
		if (this.orderItems.size() == 0)
		{
			return 2;
		}
		
		this.getOrderItems();
		
		double price = 0.00;
		for (MenuItem orderitems : orderItems) {
			price += orderitems.getPrice();
		}
		
		this.initialPrice = price;
		System.out.printf("Price before discount, service charge and taxes: $%.2f\n", initialPrice);
		
		System.out.println("Select discount to apply to invoice (-1 to go back):");
		DiscountApp.printDiscount();
		int choice = ErrorApp.safeInteger();
		if (choice == -1)
		{
			return 0;
		}
		else if (1<=choice && choice<=DiscountApp.getDiscountSize())
		{
			Discount discount = DiscountApp.getDiscountbyID(choice);
			double returnval = discount.applyDiscount(price);
			if (returnval == -1.00)
			{
				return -1;
			}
			this.discounted = returnval;
			//System.out.println("Applying discount...");
		}
		else if (choice == 0)
		{
			this.discounted = price;
		}
		else
		{
			System.out.println("Invalid selection.");
			return 0;
		}
		
		this.revenue = Taxes.applyServiceCharge(discounted);
		this.finalPrice = Taxes.applyGST(revenue);
		return 1;
	}
	
	/**
	 * get an invoice's date value
	 * @return date of invoice
	 */
	public LocalDate getInvoiceDate() {
		return date;
	}

	/**
	 * get an invoice's time value
	 * @return time of invoice
	 */
	public LocalTime getInvoiceTime() {
		return time;
	}
	
	/**
	 * get the table number of an invoice
	 * @return table number of invoice
	 */
	public int getTable() {
		return table;
	}
	
	/**
	 * get the items listed in the order for the invoice
	 * @return lists of items in order
	 */
	public ArrayList<MenuItem> getOrderItems() {
		return orderItems;
	}
	
	/**
	 * get the original value of the order based on the menu
	 * @return initial price of the invoice
	 */
	public double getInitialPrice() {
		return initialPrice;
	}
	
	/**
	 * get the amount of discount subtracted from the initial price of the order
	 * @return discount value of the invoice
	 */
	public double getDiscounted() {
		return discounted;
	}
	
	/**
	 * get the total revenue made through this transaction
	 * calculated before gst
	 * @return revenue made via the transaction (invoice)
	 */
	public double getRevenue() {
		return revenue;
	}
	
	/**
	 * get the full amount the customer has to pay for the transaction
	 * @return final price of the transaction (invoice)
	 */
	public double getFinalPrice() {
		return finalPrice;
	}
}
