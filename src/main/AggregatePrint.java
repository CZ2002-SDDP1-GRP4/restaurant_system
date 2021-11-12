package main;

import java.util.ArrayList;

import main.Menu.MenuItem;

public abstract class AggregatePrint {
	protected static ArrayList<MenuItem> grpedSaleItems;
	protected static int[] grpedSaleQty;
	
	public AggregatePrint() {
		grpedSaleItems = new ArrayList<MenuItem>();
	}
	
	protected abstract void process();
	
	protected static void groupSaleItems(ArrayList<MenuItem> ungroupedSaleItems) {
		grpedSaleItems.clear();
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
	
	protected abstract void print();
	
}
