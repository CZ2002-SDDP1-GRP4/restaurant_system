package main.Discount;

/**
 * A class representing a percent discount.
 * It is a concrete class that inherits from the abstract Discount class.
 */
public class PercentDiscount extends Discount {
	
	/**
	 * Public constructor to instantiate a percent discount object. Calls the parent constructor.
	 * @param name of discount
	 * @param rate of discount
	 */
	public PercentDiscount(String name, double rate) {
		super(name, rate);
	}
	
	/**
	 * A method implementing the abstract print discount method, 
	 * adding a percentage sign to the back indicating it is a percent discount.
	 * The discount sign is repeated to escape itself. We *100 because rate is 0-1
	 * 
	 * @Override
	 */
	public void printDiscountRate() {
		System.out.printf(rate*100 + "%%");
	}
	
	/**
	 * A concrete class implementing the abstract apply discount method, 
	 * multiplying the rate to the final price since this is a percent discount,
	 * but not before doing the relevant checks.
	 * @param price before discount
	 * @return final price after discount
	 * {@link Discount#validateDiscount()}
	 *
	 * @Override
	 */
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
