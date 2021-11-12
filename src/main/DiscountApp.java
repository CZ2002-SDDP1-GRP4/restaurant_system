package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Discount.Discount;
import main.Discount.FlatDiscount;
import main.Discount.PercentDiscount;

public class DiscountApp 
{
	private static ArrayList<Discount> discounts;
	
	public DiscountApp() {
		discounts = new ArrayList<Discount>();
	}
	
	public void createDiscount() {
		System.out.println("Type the name of the discount");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		Discount discount = null;
		System.out.println(	"What type of discount is this?\n" +
							"(1) Flat discount\n" +
							"(2) Percentage discount");
		int choice = ErrorApp.safeInteger();
		if (choice == -1) return;
		if (choice != 1 && choice != 2)
		{
			System.out.println("Invalid selection");
			return;
		}
		if (choice == 1)
		{
			System.out.println("Enter the amount of money this discount, " + name + ", deducts: ");
			double flatrate = ErrorApp.safeDouble();
			if (flatrate == (double)-1) return;
			discount = new FlatDiscount(name, flatrate);
		}
		else if (choice == 2)
		{
			System.out.println("Enter the percentage off this discount, " + name + ", applies: ");
			double percentrate = ErrorApp.safeDouble();
			if (percentrate == (double)-1) return;
			discount = new PercentDiscount(name, percentrate);
		}
		discounts.add(discount);
		System.out.println("Discount added");
		
	}
	
	public static void printDiscount() {
		if (discounts.size() == 0)
		{
			System.out.println("No discounts available.\n");
			return;
		}
		for (Discount discount : discounts)
		{
			System.out.printf("Name: " + discount.getDiscountName() + ", ");
			discount.printDiscountRate();
			System.out.printf("\n");
		}
	}
	
	public static int getDiscountSize() {
		return discounts.size();
	}

	public static Discount getDiscountbyID(int choice) {
		return discounts.get(choice-1);
	}
	
}
