package main.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.DiscountApp;
import main.ErrorApp;
import main.Discount.Discount;
import main.Menu.MenuItem;
import main.Staff.Staff;

public class OrderInvoice {
	private LocalDate date;
	private LocalTime time;
	private int table;
	private ArrayList<MenuItem> orderItems;
	private double initialPrice;
	private double discounted;
	private double revenue;
	private double finalPrice;
	
	
	public OrderInvoice(Order OrderToProcess) {
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.table = OrderToProcess.getOrderTable();
		this.orderItems = OrderToProcess.getOrderItems();
	}
	
	public int processInvoice() {
		
		this.getOrderItems();
		
		double price = 0.00;
		for (MenuItem orderitems : orderItems) {
			price += orderitems.getPrice();
		}
		
		this.initialPrice = price;
		System.out.printf("Price before discount, service charge and taxes: $%.2f\n", initialPrice);
		
		DiscountApp.printDiscount();		
		System.out.println("Select discount to apply to invoice (-1 to go back):");
		int choice = ErrorApp.safeInteger();
		if (choice == -1)
		{
			return 0;
		}
		else if (1<=choice && choice<=DiscountApp.getDiscountSize())
		{
			Discount discount = DiscountApp.getDiscountbyID(choice);
			this.discounted = discount.applyDiscount(price);
			System.out.println("Applying discount...");
		}
		else
		{
			System.out.println("Invalid selection.");
			return 0;
		}
		
		System.out.println("Price before service charge and taxes: " + discounted);
		this.revenue = Taxes.applyServiceCharge(discounted);
		this.finalPrice = Taxes.applyGST(revenue);
		return 1;
	}
	
	public LocalDate getInvoiceDate() {
		return date;
	}

	public LocalTime getInvoiceTime() {
		return time;
	}
	
	public int getTable() {
		return table;
	}
	
	public ArrayList<MenuItem> getOrderItems() {
		return orderItems;
	}
	
	public double getInitialPrice() {
		return initialPrice;
	}
	
	public double getDiscounted() {
		return discounted;
	}
	
	public double getRevenue() {
		return revenue;
	}
	
	public double getFinalPrice() {
		return finalPrice;
	}
}
