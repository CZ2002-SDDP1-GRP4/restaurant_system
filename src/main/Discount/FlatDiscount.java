package main.Discount;

public class FlatDiscount extends Discount {
	
	public FlatDiscount(String name, double rate) {
		super(name, rate);
	}
	
	@Override
	public void printDiscountRate() {
		System.out.printf("$" + rate);
	}
	
	@Override
	public double applyDiscount(double price) {
		//System.out.println("flat discount" + rate);
		double finalprice = price-rate;
		int choice = -2;
		if (finalprice <= 0.00)
		{
			choice = validateDiscount();
		}
		if (choice == 1)
		{
			System.out.println("Discount applied");
			finalprice = 0.00;
			return finalprice;
		}
		else if (choice == 2)
		{
			System.out.println("Discount not applied.");
			return price;
		}
		else if (choice == -1)
		{
			return -1.00;
		}
		return finalprice;
	}

}
