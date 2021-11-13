package main.Order;

import main.StaffApp;

/**
 * A class to define and apply taxes.
 * @author Bryan
 * @version 1.0
 */
public class Taxes {
	
	/**
	 * A final double indicating 10% service charge.
	 */
	private static final double SERVICE_CHARGE = 0.1;
	/**
	 * A final double indicating 7% Goods and Serivces Tax.
	 */
	private static final double GST = 0.07;

	/**
	 * A method to apply service charge.
	 * @param original price
	 * @return price with service charge
	 */
	public static double applyServiceCharge(double price) {
		return price*(1+SERVICE_CHARGE);
	}
	
	/**
	 * A method to apply GST.
	 * @param original price
	 * @return price with GST
	 */
	public static double applyGST(double price) {
		return price*(1+GST);
	}

}
