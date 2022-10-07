package com.techelevator;

import com.techelevator.view.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
//	private static final String[] SLOT_POSITION = {"A1 Chip Crisp", "A2 - Stackers", "A3 - Grain Waves", "A4 - Cloud Popcorn", "B1 - Moonpie", "B2 - Cowtales", "B3 - Wonka Bar", "B4 - Crunchie", "C1 - Cola", "C2 - Dr. Salt", "C3 - Mountain Melter", "C4 - Heavy", "D1 - U-Chews", "D2 - Little League Chew", "D3 - Chiclets", "D4 - Triplemint"};
////	private static final String[] test = {vendingMachineMap.toString().}

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


				//Writing transaction log to Log.txt
				File targetFile = new File("src", "Log.txt");
				SimpleDateFormat format = new SimpleDateFormat("MM-DD-YYYY HH:mm:ss");
				try(PrintWriter writer = new PrintWriter(targetFile)){
					writer.println(format);
				} catch (FileNotFoundException e) {
					System.out.println("File not found");;
				}

				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				//if customer chooses purchase, chooses feed money option
				if(purchaseChoice.equals(PURCHASE_MENU_FEED_OPTION)){

					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());
					Double amountOfMoney = (Double) menu.getChoiceFromOptions(MONEY_CUSTOMER_CAN_ENTER);

					customerPurchase.feedMoney(amountOfMoney);
					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());


				} else if(purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)){

					//Prints out the List of Items, their location, name, and price
					for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
						System.out.println(item.getValue().getLocation() + "| " + item.getValue().getName() + " - $" + item.getValue().getPrice());
					}

					//Customer selects which item they want from the list of KEYS
					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());
					System.out.print("Please choose a product: ");
					String chooseLocation = customerInput.nextLine();

					//Bring in Item class and assign Customer's Selection to Item
					Item itemSelection = vendingMachineMap.get(chooseLocation);
					System.out.println(itemSelection.getName() + " " + itemSelection.getPrice());

					// If Item Selected is in Stock, Subtract Customer Money and Quantity --1
					if(itemSelection.getQuantity() > 0){
						if(customerPurchase.getCurrentMoneyProvided() >= itemSelection.getPrice()){
							customerPurchase.purchaseItem(itemSelection.getPrice());
							itemSelection.dispenseItem();


							System.out.println("Remaining total :" + customerPurchase.getCurrentMoneyProvided());
						} else {
							System.out.println("Insert More Money to Purchase Item");
						}

						//If item is out of Stock, Print Error Message
					} else {
						System.out.println("Sorry, this item is out of stock.");
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
