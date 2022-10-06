package com.techelevator;

import com.techelevator.view.Beverages;
import com.techelevator.view.Menu;

public class VendingMachine {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT_OPTION = "Exit";
	private static final String SUB_MENU_FEED_OPTION = "Feed Money";
	private static final String SUB_MENU_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_FINISH = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION };
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_FEED_OPTION, SUB_MENU_SELECT_PRODUCT, SUB_MENU_FINISH };

	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine cli = new VendingMachine(menu);
		cli.run();
	}

	public VendingMachine(Menu menu) {
		this.menu = menu;
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
