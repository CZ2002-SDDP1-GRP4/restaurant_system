package main.Discount;

/**
 * A class representing a flat discount.
 * It is a concrete class that inherits from the abstract Discount class.
 */
public class FlatDiscount extends Discount {
	
	/**
	 * Public constructor to instantiate a flat discount object. Calls the parent constructor.
	 * @param name of discount
	 * @param rate of discount
	 */
	public FlatDiscount(String name, double rate) {
		super(name, rate);
	}
	
	/**
	 * A method implementing the abstract print discount method, 
	 * adding a dollar sign to the front indicating it is a flat discount.
	 * 
	 * @Override
	 */
	public void printDiscountRate() {
		System.out.printf("$" + rate);
	}
	
	/**
	 * A concrete class implementing the abstract apply discount method, 
	 * minusing the rate from the final price since this is a flat discount,
	 * but not before doing the relevant checks.
	 * @param price before discount
	 * @return final price after discount
	 * {@link Discount#validateDiscount()}
	 *
	 * @Override
	 */
	public double applyDiscount(double price) {
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
