package com.techelevator;

import com.techelevator.view.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine{

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT_OPTION = "Exit";
	private static final String PURCHASE_MENU_FEED_OPTION = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_OPTION, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH };
	private static final Double[] MONEY_CUSTOMER_CAN_ENTER = {1.00,2.00,5.00,10.00,20.00};
	private static final String[] SLOT_POSITION = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"};





	public static Map<String, Item> vendingMachineMap = new LinkedHashMap<>();

	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		VendingMachine cli = new VendingMachine(menu);

		readFileAndMappingItems();

		System.out.println();
		cli.run();
	}

	public VendingMachine(Menu menu){
		this.menu = menu;
	}

//Reads the Document of Items to put in the vending machine, and distributes them to classes
	public static void readFileAndMappingItems(){
		File fileToBeRead = new File("vendingmachine.csv");
		try (Scanner fileOpener = new Scanner(fileToBeRead)) {
			while (fileOpener.hasNextLine()) {
				String lineOfText = fileOpener.nextLine();
				String [] fileLine = lineOfText.split("\\|");

					if(fileLine[3].equals("Chip")){
						Chips chipItems = new Chips(fileLine[0], fileLine[1], Double.parseDouble(fileLine[2]));
						vendingMachineMap.put(fileLine[0], chipItems);

					} else if(fileLine[3].equals("Candy")){
					Candy candyItem = new Candy(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
					vendingMachineMap.put(fileLine[0], candyItem);
					}

					else if(fileLine[3].equals("Drink")){
						Drink beveragesItem = new Drink(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
						vendingMachineMap.put(fileLine[0], beveragesItem);


					} else if(fileLine[3].equals("Gum")){
						Gum gumItem = new Gum(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
						vendingMachineMap.put(fileLine[0], gumItem);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Product list not found");
		}

	}


//Running our code - This method runs in PSVM
	public void run() {

		//Imports the Scanner
		Scanner customerInput = new Scanner(System.in);

		// ===== you nay use/modify the existing Menu class or write your own ======
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			Purchase customerPurchase = new Purchase();

			//if customer chooses display items, Map is shown with items
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
					System.out.println(item.getValue().toString().trim());
				}



				//if customer chooses purchase, purchase menu is shown to customer
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				//if customer chooses purchase, chooses feed money option
				if(purchaseChoice.equals(PURCHASE_MENU_FEED_OPTION)){


					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());
					Double amountOfMoney = (Double) menu.getChoiceFromOptions(MONEY_CUSTOMER_CAN_ENTER);


					customerPurchase.feedMoney(amountOfMoney);
					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());


				} else if(purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)){

					for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
						System.out.println(item.getValue().getLocation() + "| " + item.getValue().getName() + " - $" + item.getValue().getPrice());


						String chooseLocation = (String) menu.getChoiceFromOptions(SLOT_POSITION);
						Item itemChoice;
						itemChoice.getClass(chooseLocation);

						if(chooseLocation.equals("A1")){
							System.out.println(itemChoice.getName());
						}




					}




				} else if(purchaseChoice.equals(PURCHASE_MENU_FINISH)){
					// Return the customer their money, Reset current balance to 0
					customerPurchase.getChange();
					return;
				}

			} else if (choice.equals(MAIN_MENU_EXIT_OPTION)){
				System.exit(1);
			}

		}

	}

}
