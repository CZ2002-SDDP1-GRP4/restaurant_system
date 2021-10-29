package main;
import java.util.ArrayList;
import java.util.Scanner;
import main.Menu.*;

public class MenuApp 
{
	private ArrayList<MenuItem> restaurantCatalog;
	private ArrayList<Menu> menus;
	public MenuApp(){
		restaurantCatalog = new ArrayList<MenuItem>() ;	
		menus = new ArrayList<Menu>() ;	
	}
	public void getFunctions(){
		System.out.println("What do u wanna do");
		System.out.println("1. Create Menu");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch(choice){
			case 1:
				createMenu();
				break;
		}
	}

	public void createMenu(){
		menus.add(new Menu());

	}
}
