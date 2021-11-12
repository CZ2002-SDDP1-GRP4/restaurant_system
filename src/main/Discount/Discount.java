package main.Discount;

import main.ErrorApp;

public abstract class Discount {
	private String name;
	protected double rate;

	public Discount(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}
	
	public String getDiscountName() {
		return name;
	}
	
	public void setDiscountRate(double rate) {
		this.rate = rate;
	}

	public double getDiscountRate() {
		return rate;
	}
	
	public abstract void printDiscountRate();
	
	public abstract double applyDiscount(double price);

	protected int validateDiscount() {
		System.out.println(	"Note: applying this discount will lead to a price of $0.00!\n" +
							" (1) Confirm that this is valid (payable = $0.00)\n" +
							" (2) Do not apply discount\n" +
							" (-1) Exit"
							);
		int choice = ErrorApp.safeInteger();
		return choice;
	}
	
}
