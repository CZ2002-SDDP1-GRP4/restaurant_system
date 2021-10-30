package main.Discount;

public class PercentDiscount extends Discount{
	
	private double percent;
	
	public PercentDiscount(String name) {
		super(name);
	}

	public double applyDiscount(double totalbill) {
		
		return percent*100*totalbill;
	}
	
	public void setRate(double rate) {
		this.percent = rate;
	}
	
	public double getRate() {
		return this.percent;
	}
}
