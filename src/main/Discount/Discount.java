package main.Discount;

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

}
