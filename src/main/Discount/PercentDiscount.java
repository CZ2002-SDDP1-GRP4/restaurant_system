package main.Discount;

public class PercentDiscount extends Discount {
	
	public PercentDiscount(String name, double rate) {
		super(name, rate);
	}
	
	@Override
	public void printDiscountRate() {
		System.out.printf(rate*100 + "%%");
	}
	
	@Override
	public double applyDiscount(double price) {
		//System.out.println("percent discount" + rate);
		double finalprice = price*(1.00-rate);
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
