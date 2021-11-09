package main.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import main.Menu.MenuItem;
import main.Staff.Staff;

public class OrderInvoice {
	private LocalDate date;
	private LocalTime time;
	private Order order;
	private Discount discount;
	private double totalPrice;
	private double finalPrice;
	
	
	public OrderInvoice(Order OrderToProcess) {
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.order = OrderToProcess;
		this.discount = null;
	}
	
	public OrderInvoice processInvoice() {
		double price = 0.00;
		for (MenuItem orderitems : this.order.getOrderItems()) {
			price += orderitems.getPrice();
		}
		this.totalPrice = price;
		System.out.println("Total price before discount is: " + price);
		System.out.println(	"Does the customer have a membership?\n" +
							"(1) Yes\n" +
							"(2) No");
		Scanner sc = new Scanner(System.in);
		int choice;
		do
		{
			choice = sc.nextInt();
			sc.nextLine(); //throw away the \n not consumed by nextInt()
			if (choice == 1)
			{
				System.out.println("Applying member discount");
				price = price-2;
				//double finalprice = applyDiscount(price);
			}
			else if (choice == 2)
			{
				continue;
			}
			else 
			{
				System.out.println("Invalid choice, try again?");
			}
		} while (choice != 1 && choice != 2);
		System.out.println("Final price is " + price);
		this.finalPrice = price;
		return this;
	}
	
	public LocalDate getInvoiceDate() {
		return date;
	}

	public LocalTime getInvoiceTime() {
		return time;
	}
	
	public void printInvoiceOrders() {
		order.printDetailedOrder();
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public double getFinalPrice() {
		return finalPrice;
	}
}
