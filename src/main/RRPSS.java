package main;
import java.util.Scanner;

public class RRPSS 
{
	public static void main(String[] args) 
	{
		MenuApp restaurantMenu = new MenuApp();
		ReservationApp reservation = new ReservationApp();
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
					restaurantMenu.getFunctions();
					break;
				case 2:
					reservation.getFunctions();
					break;
			}
		} while (choice <= 10);
	}
}
