package com.techelevator;

import com.techelevator.view.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
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
	private static final Double[] MONEY_CUSTOMER_CAN_ENTER = {1.00, 2.00, 5.00, 10.00, 20.00};


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

    //reads item document and adds items to classes
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
					writeFeedToFile(amountOfMoney, customerPurchase.getCurrentMoneyProvided());
					System.out.println("Current Money Provided: " + customerPurchase.getCurrentMoneyProvided());


				} else if(purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)){

					//Prints out the List of Items, their location, name, and price
					for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
						System.out.println(item.getValue().getLocation() + "| " + item.getValue().getName() + " - $" + item.getValue().getPrice());
					}

					//Customer selects which item they want
					String currentMoneyProvided = String.format("%.2f", customerPurchase.getCurrentMoneyProvided());
					System.out.printf("Current Money Provided: " + currentMoneyProvided);
					System.out.println();
					System.out.print("Please choose a product: ");

					//Customer enters item Location - Case insensitive
					String chooseLocation = customerInput.nextLine().toUpperCase();

					//Bring in Item class and assign Customer's Selection to Item
					if(!vendingMachineMap.containsKey(chooseLocation)){
						System.out.println("That location is invalid, please try again.");
						purchaseChoice.equals(menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS));
						continue;
					}

					Item itemSelection = vendingMachineMap.get(chooseLocation);


					// if item is in stock, customer purchases item and machine dispenses
					if(itemSelection.getQuantity() > 0){
						if(customerPurchase.getCurrentMoneyProvided() >= itemSelection.getPrice()){
							purchaseItem(customerPurchase, itemSelection);

						} else {
							System.out.println("Insert More Money to Purchase Item");
						}

					//If item is out of Stock, Print Error Message
					} else {
						System.out.println("Sorry, this item is out of stock.");
					}


				} else if(purchaseChoice.equals(PURCHASE_MENU_FINISH)){
					// Return the customer their money, Reset current balance to 0
					writeGiveChangeToFile(customerPurchase.getCurrentMoneyProvided());

					System.out.println(customerPurchase.returnChange());

				}

			} else if (choice.equals(MAIN_MENU_EXIT_OPTION)){
				System.exit(1);
			}

		}

	}


	//method to call when customer purchases item
	public void purchaseItem(Purchase customerPurchase, Item itemSelection){
		//Subtracts money from Customer's Total
		customerPurchase.purchaseItem(itemSelection.getPrice());

		// prints Item sound and subtracts from quantity
		itemSelection.dispenseItem(itemSelection);

		//writes purchase info to Log.txt
		writePurchaseToFile(customerPurchase, itemSelection);

		//prints how much money customer has left
		String remainingTotal = String.format("%.2f", customerPurchase.getCurrentMoneyProvided());
		System.out.println("Remaining total :" + remainingTotal);

	}

	//methods that write to Log.txt
	public void writeFeedToFile(double moneyFed, double totalCustomerMoney){
		//Writing transaction log to Log.txt
		File targetFile = new File("src", "Log.txt");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		String dateString = dateFormat.format(new Date()).toString();

		try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
			writer.println(dateString + " FEED MONEY: $" + moneyFed + " $" + totalCustomerMoney);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");;
		}
	}
	public void writeGiveChangeToFile(double customerTotalMoney){
		//Writing transaction log to Log.txt
		File targetFile = new File("src", "Log.txt");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		String dateString = dateFormat.format(new Date()).toString();

		try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
			writer.println(dateString + " GIVE CHANGE: $" + customerTotalMoney + " $0.00");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");;
		}
	}
	public void writePurchaseToFile(Purchase customerPurchase, Item itemSelection){
		//Writing transaction log to Log.txt
		File targetFile = new File("src", "Log.txt");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		String dateString = dateFormat.format(new Date()).toString();

		try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
			writer.println(dateString + " " + itemSelection.getName() + " "
					+ itemSelection.getLocation() + " " + itemSelection.getPrice() + " "
					+ customerPurchase.getCurrentMoneyProvided());
		} catch (FileNotFoundException e) {
			System.out.println("File not found");;
		}
	}



}

