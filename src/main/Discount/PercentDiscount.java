package main.Discount;

public class PercentDiscount extends Discount {
	
	public PercentDiscount(String name, double rate) {
		super(name, rate);
	}
	
	@Override
	public void printDiscountRate() {
		System.out.printf("$" + rate);
	}
	
	@Override
	public double applyDiscount(double price) {
		return price*rate;
	}
}
