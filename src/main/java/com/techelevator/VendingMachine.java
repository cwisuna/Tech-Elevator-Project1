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
	private static final String PURCHASE_MENU_FEED_OPTION = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_OPTION, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH };
	private static final Double[] MONEY = {1.00, 2.00, 5.00, 10.00, 20.00};

	public static Map<String, Item> mapOfVendingMachine = new HashMap<>();



	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		VendingMachine cli = new VendingMachine(menu);
		readFileAndMappingItems();

		System.out.println();
		cli.run();
	}

	public VendingMachine(Menu menu) {
		this.menu = menu;
	}

//Reads the Document of Items to put in the vending machine, and distributes them to classes
	public static void readFileAndMappingItems(){
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

					else if(fileLine[3].equals("Drink")){
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


//Running our code - This method runs in PSVM
	public void run() {

		//Imports the Scanner
		Scanner customerInput = new Scanner(System.in);

		// ===== you nay use/modify the existing Menu class or write your own ======
		while (true) {
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			//bring in Purchase class to take in, hand out money, and select item
			    Purchase customerPurchase = new Purchase();


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for(Map.Entry<String, Item> item : mapOfVendingMachine.entrySet())
				System.out.print(item.getValue().toString());

				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				//This String selects what button to press from the Purchase Menu
				System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());
				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);


				//Customer selects FEED MONEY option from Purchase menu
				if(purchaseChoice.equals(PURCHASE_MENU_FEED_OPTION)){

						//Customer Selects Money Amount Inserted
						Double amountOfMoney = (Double) menu.getChoiceFromOptions(MONEY);

						//Feeds Money Into Machine
						customerPurchase.feedMoney(amountOfMoney);



				} else if(choice.equals(PURCHASE_MENU_SELECT_PRODUCT)){
					//Allow user to choose an item

					System.out.println(customerPurchase.currentMoneyProvided);



				} else if(choice.equals(PURCHASE_MENU_FINISH)){
					// Return the customer their money, Reset current balance to 0
					customerPurchase.getChange();
					return;
				}
																			// do purchase
			} else if (choice.equals(MAIN_MENU_EXIT_OPTION)){
				System.exit(1);
			}
			  																//will exit
		}

	}

}
