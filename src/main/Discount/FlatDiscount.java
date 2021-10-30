package main.Discount;

public class FlatDiscount extends Discount {

	private double flatrate;
	
	public FlatDiscount(String name) {
		super(name);
	}

	public double applyDiscount(double totalbill) {
		
		return totalbill - this.flatrate;
	}
	
	public void setRate(double rate) {
		this.flatrate = rate;
	}

	public double getRate() {
		return this.flatrate;
	}
}
