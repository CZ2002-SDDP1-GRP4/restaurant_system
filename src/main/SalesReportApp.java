package main;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import main.Menu.MenuItem;
import main.Order.OrderInvoice;

public class SalesReportApp {
	private static ArrayList<OrderInvoice> orderInvoices;
	private ArrayList<MenuItem> grpedSaleItems;
	private int[] grpedSaleQty;
	private double totalRevenue;

	public SalesReportApp() {
		orderInvoices = new ArrayList<OrderInvoice>();
		grpedSaleItems = new ArrayList<MenuItem>();
		totalRevenue = 0;
	}
	
	public static void addToReport(OrderInvoice orderInvoice) {
		orderInvoices.add(orderInvoice);
	}
	
	public void getPrintUI() {
		System.out.println("By (1) Day (2) Month");
		int choice = ErrorApp.safeInteger();
		if (choice == 1)
		{
			System.out.println("Input date (eg. 2021-07-30):");
	        LocalDate date = ErrorApp.dateHandler();
	        processReport(date);
		}
		else if (choice == 2)
		{
			System.out.println("Select month: (-1 to return)\n"
							 + "(1) January\n"
							 + "(2) Feburary\n"
							 + "(3) March\n"
							 + "(4) April\n"
							 + "(5) May\n"
							 + "(6) June\n"
							 + "(7) July\n"
							 + "(8) August\n"
							 + "(9) September\n"
							 + "(10) October\n"
							 + "(11) November\n"
							 + "(12) December");
	        int month = ErrorApp.safeInteger();
	        if (month == -1) return;
	        else if (1 > month || month > 12)
	        {
	        	System.out.println("Invalid choice");
	        }
	        else processReport(month);
		}
		else if (choice == -1) return;
	}

	private void processReport(int month) {
		ArrayList<MenuItem> ungroupedSaleItems = new ArrayList<MenuItem>();
		totalRevenue = 0;
		for (OrderInvoice orderinvoice : orderInvoices)
		{
			if (month == orderinvoice.getInvoiceDate().getMonthValue())
			{
				ungroupedSaleItems.addAll(orderinvoice.getOrderItems());
				totalRevenue += orderinvoice.getRevenue();
			}
		}
		groupSaleItems(ungroupedSaleItems);
		System.out.println("Sales Revenue Report (for " + Month.of(month) + ")");
		printReport();
	}

	private void processReport(LocalDate date) {
		totalRevenue = 0;
		ArrayList<MenuItem> ungroupedSaleItems = new ArrayList<MenuItem>();
		for (OrderInvoice orderinvoice : orderInvoices)
		{
			if (date.isEqual(orderinvoice.getInvoiceDate()))
			{
				ungroupedSaleItems.addAll(orderinvoice.getOrderItems());
				totalRevenue += orderinvoice.getRevenue();
			}
		}
		groupSaleItems(ungroupedSaleItems);
		System.out.println("Sales Revenue Report (for " + date + ")");
		printReport();
	}
	
	private void groupSaleItems(ArrayList<MenuItem> ungroupedSaleItems) {
		for (MenuItem ungroupedItem : ungroupedSaleItems)
		{
			if (!grpedSaleItems.contains(ungroupedItem))
			{
				grpedSaleItems.add(ungroupedItem);
			}
		}
		grpedSaleQty = new int[grpedSaleItems.size()];
		for (int i = 0; i < grpedSaleItems.size(); i++)
		{
			MenuItem cur = grpedSaleItems.get(i);
			for (MenuItem ungroupedItem : ungroupedSaleItems)
			{
				if (ungroupedItem.equals(cur))
				{
					grpedSaleQty[i]++;
				}
			}
		}
	}
	
	private void printReport() {
		System.out.println("Total revenue is " + totalRevenue);
		int i = 0;
		for (MenuItem saleItem : grpedSaleItems)
		{
			System.out.println(String.format("%-26s" + "%7s\n", saleItem.getName(), grpedSaleQty[i++]));
		}
		
	}
	
}
