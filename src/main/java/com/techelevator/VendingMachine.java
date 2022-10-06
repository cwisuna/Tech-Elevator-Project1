package com.techelevator;

import com.techelevator.view.Beverages;
import com.techelevator.view.Menu;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine{

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT_OPTION = "Exit";
	private static final String SUB_MENU_FEED_OPTION = "Feed Money";
	private static final String SUB_MENU_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_FINISH = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION };
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_FEED_OPTION, SUB_MENU_SELECT_PRODUCT, SUB_MENU_FINISH };

	static Map<String, List> mapOfVendingMachine = new HashMap<>();
	public static List listOfItemsAndPrices = new ArrayList();
	public static List listOfChips = new ArrayList();
	public static List listOfCandy = new ArrayList();
	public static List listOfDrinks = new ArrayList();
	public static List listOfGum = new ArrayList();



	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		VendingMachine cli = new VendingMachine(menu);
		readFile();
		System.out.println(listOfCandy);
		cli.run();
	}

	public VendingMachine(Menu menu) {
		this.menu = menu;
	}


	public static void readFile(){
		File readTheFile = new File("vendingmachine.csv");
		try (Scanner dataInput = new Scanner(readTheFile)) {
			while (dataInput.hasNextLine()) {
				String lineOfInput = dataInput.nextLine();
				String [] fileLine = lineOfInput.split("\\|");
				for(int i = 0; i < 1; i++){
					listOfItemsAndPrices.add(fileLine[1] + " " + fileLine [2]);
					mapOfVendingMachine.put(fileLine[i], listOfItemsAndPrices);

					if(fileLine[3].equals("Chip")){
						listOfChips.add(fileLine[0] + ", " + fileLine[1] + ", " + fileLine[2]);
					} else if(fileLine[3].equals("Candy")){
						listOfCandy.add(fileLine[0] + ", " + fileLine[1] + ", " + fileLine[2]);
					} else if(fileLine[3].equals("Drink")){
						listOfDrinks.add(fileLine[0] + ", " + fileLine[1] + ", " + fileLine[2]);
					} else if(fileLine[3].equals("Gum")){
						listOfGum.add(fileLine[0] + ", " + fileLine[1] + ", " + fileLine[2]);
					}
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("Product list not found");
		}

	}

	public void run() {




		// ===== you nay use/modify the existing Menu class or write your own ======
		while (true) {

				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				 															 // display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String subChoice = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
																			// do purchase
			} else if (choice.equals(MAIN_MENU_EXIT_OPTION)){
				System.exit(1);
			}
			  																//will exit
		}

	}

}
