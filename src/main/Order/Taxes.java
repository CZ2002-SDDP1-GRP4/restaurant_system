package main.Order;

public class Taxes {
	
	private static final double SERVICE_CHARGE = 0.1;
	private static final double GST = 0.07;

	public static double applyServiceCharge(double price) {
		return price*(1+SERVICE_CHARGE);
	}
	
	public static double applyGST(double price) {
		return price*(1+GST);
	}

}
