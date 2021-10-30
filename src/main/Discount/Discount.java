package main.Discount;

public abstract class Discount {
		
	private String name;
	
	public Discount(String name) {
	
		this.name = name;
	}
	
	public String getName(){
		
		return this.name;	
	}
	
	public abstract void setRate(double rate);
	
	public abstract double applyDiscount(double totalbill);
	
}
