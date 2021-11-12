package main.Discount;

public class FlatDiscount extends Discount {
	
	public FlatDiscount(String name, double rate) {
		super(name, rate);
	}
	
	@Override
	public void printDiscountRate() {
		System.out.printf(rate + "%%");
	}
	
	@Override
	public double applyDiscount(double price) {
		return price-rate;
	}
}
