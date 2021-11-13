package main.Discount;

import main.ErrorApp;

/**
 * An abstract class defining a discount.
 * This class is abstract because there are common attributes and methods shared by all discounts,
 * such as name, discount rate, getters/setters, and applying discounts,
 * but their method of application varies depending on the discount type.
 * @author Bryan
 * @version 1.0
 */
public abstract class Discount {
	/**
	 * An attribute defining discount name.
	 */
	private String name;
	/*
	 * A protected attribute defining the discount rate.
	 */
	protected double rate;

	/**
	 * Public constructor to instantiate a discount object.
	 * This will be called first before the constructor of the subclasses
	 * @param name of discount
	 * @param rate of discount
	 */
	public Discount(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}
	
	/**
	 * Getter method for discount name
	 * @return name of discount
	 */
	public String getDiscountName() {
		return name;
	}
	
	/**
	 * Setter method for discount rate
	 */
	public void setDiscountRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Getter method for discount rate
	 * @retrun rate of discount
	 */
	public double getDiscountRate() {
		return rate;
	}
	
	/**
	 * An abstract method to print the discount rate.
	 * This is abstract since the discount printed will 
	 * need to understand how the discount is applied,
	 * e.g. X% or $X.XX
	 */
	public abstract void printDiscountRate();
	
	/**
	 * An abstract method to apply the discount.
	 * @param price before discount
	 * @return final price after discount
	 */
	public abstract double applyDiscount(double price);

	/**
	 * A protected method to validate if a discount makes sense.
	 * This will issue a warning if the price after discount will be $0.00 (or less)
	 * As this is not impossible to occur in real life e.g. a free walk-in meal promotion, 
	 * we will allow for it but only upon explicit consent.
	 * @return choice to proceed/reject the $0.00 price
	 */
	protected int validateDiscount() {
		System.out.println(	"Note: applying this discount will lead to a price of $0.00!\n" +
							" (1) Confirm that this is valid (payable = $0.00)\n" +
							" (2) Do not apply discount\n" +
							" (-1) Exit"
							);
		int choice = ErrorApp.safeInteger();
		return choice;
	}
	
}
