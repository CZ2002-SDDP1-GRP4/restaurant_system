package main;

import java.util.ArrayList;

import main.Menu.MenuItem;

/**
 * An abstract class defining an object that does three things:
 * 1) Processes an ungrouped array of objects including MenuItems
 * 2) Aggregates MenuItems with Quantity.
 * 3) Prints those MenuItems with Quantity.
 * This abstract class will be used by the OrderInvoiceApp and SalesReportApp to process, aggregate and print.
 * They share common attributes such as an array of MenuItems to print and an array indicating quantity,
 * and a common method to aggregate MenuItems with Quantity, but their processing to extract the relevant
 * MenuItems and their printing are different (e.g. OrderInvoice needs to show table number and time and discounts etc,
 * but SalesReport only needs the revenue)
 * @author Bryan
 * @version 1.0
 */
public abstract class AggregatePrint {
	protected static ArrayList<MenuItem> grpedSaleItems;
	protected static int[] grpedSaleQty;
	
	/**
	 * Public constructor to initialise the array list of MenuItems for aggregating
	 */
	public AggregatePrint() {
		grpedSaleItems = new ArrayList<MenuItem>();
	}
	
	/**
	 * Abstract method to process an object to get ready for grouping Sale Items.
	 * See the subclasses for their exact implementation
	 */
	protected abstract void process();
	
	/**
	 * Method that:
	 * 1) Reads in an array list of ungrouped Menu Items, i.e. has duplicates
	 * 2) Groups them and remembers the quantity for each Menu Item
	 * @param array list of menu items ungroupedSaleItems
	 */
	protected static void groupSaleItems(ArrayList<MenuItem> ungroupedSaleItems) {
		grpedSaleItems.clear();
		for (MenuItem ungroupedItem : ungroupedSaleItems)
		{
			String curName = ungroupedItem.getName();
			for (MenuItem grpedItem : grpedSaleItems)
			{
				if (grpedItem.getName().equals(curName))
				{
					grpedSaleItems.add(ungroupedItem);
				}
			}
		}
		grpedSaleQty = new int[grpedSaleItems.size()];
		for (int i = 0; i < grpedSaleItems.size(); i++)
		{
			MenuItem cur = grpedSaleItems.get(i);
			for (MenuItem ungroupedItem : ungroupedSaleItems)
			{
				if (ungroupedItem.getName().equals(cur.getName()))
				{
					grpedSaleQty[i]++;
				}
			}
		}
	}
	
	/**
	 * Abstract method to print out the Order Invoice / Sale Report.
	 * See the subclasses for their exact implementation.
	 */
	protected abstract void print();
	
}
