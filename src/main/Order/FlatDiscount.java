package main.Order;

import java.util.Scanner;

public class FlatDiscount extends Discount {
	private int flatrate;
	
	public FlatDiscount() {
		super();
		System.out.println("Flat discount rate: ");
		Scanner sc = new Scanner(System.in);
		flatrate = sc.nextInt();
		sc.nextLine(); //throw away the \n not consumed by nextInt()
	}
	
	public int applyDiscount(int price) {
		return price-flatrate;
	}
}
