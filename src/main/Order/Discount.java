package main.Order;

import java.util.Scanner;

public abstract class Discount {
	private String name;

	public Discount() {
		System.out.println("New discount name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
	}
	
	public String getDiscountName() {
		return name;
	}
	
	public void applyDiscount() {}

}
