package com.techelevator;

import com.techelevator.view.*;

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

	static Map<String, Item> mapOfVendingMachine = new HashMap<>();

	public static List listOfItemsAndPrices = new ArrayList();



	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		VendingMachine cli = new VendingMachine(menu);
		readFile();
		System.out.println(mapOfVendingMachine);
		System.out.println();
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

					if(fileLine[3].equals("Chip")){
						Chips chip = new Chips(fileLine[1], Double.parseDouble(fileLine[2]));
						mapOfVendingMachine.put(fileLine[0], chip);

					} else if(fileLine[3].equals("Candy")){
					Candy candy = new Candy(fileLine[1], Double.parseDouble(fileLine[2]));
					mapOfVendingMachine.put(fileLine[0], candy);
					}

					else if(fileLine[3].equals("Beverage")){
						Beverages beverages = new Beverages(fileLine[1], Double.parseDouble(fileLine[2]));
						mapOfVendingMachine.put(fileLine[0], beverages);

					} else if(fileLine[3].equals("Gum")){
						Gum gum = new Gum(fileLine[1], Double.parseDouble(fileLine[2]));
						mapOfVendingMachine.put(fileLine[0], gum);
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
