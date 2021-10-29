package main;

import java.util.Scanner;

public class MainApp 
{

	public static void main(String[] args) 
	{
		System.out.println
		(
				"(1) Create/Update/Remove Menu Item\n" +
				"(2) Create/Update/Remove Promotion\n" +
				"(3) Create Order\n" +
				"(4) View Order\n" +
				"(5) Add/Remove Order Item(s) to/from Order\n" +
				"(6) Create Reservation Booking\n" +
				"(7) Check/Remove Reservation Booking\n" +
				"(8) Check Table Availability\n" +
				"(9) Print Order Invoice\n" +
				"(10) Print Sale Revenue Report"
		);
		
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do
		{
			System.out.printf("Enter your Selection: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					main.MenuItemApp.main();
				case 2:
					
			}
		} while (choice <= 10);

	}

}
