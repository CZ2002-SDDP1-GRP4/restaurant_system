package main.Order;

import java.util.Scanner;

public class PercentDiscount extends Discount {
	private float percent;
	
	public PercentDiscount() {
		super();
		System.out.println("Discount percentage (0.00-1.00): ");
		Scanner sc = new Scanner(System.in);
		percent = sc.nextInt();
		sc.nextLine(); //throw away the \n not consumed by nextInt()
	}
	
	public float applyDiscount(int price) {
		return price*(1-percent);
	}
}
