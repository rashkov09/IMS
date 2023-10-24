package view;

import util.ConsoleRangeReader;

import static java.lang.System.exit;

public class MainView implements ConsoleView {

	private static final int MAX_CHOICE = 3;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- Welcome to Inventory Management System -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Item Management
	                                         2. Order Management
	                                         3. Payment Management
	                                                                                  
	                                         0. Exit
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				ItemManagementView itemManagementView = new ItemManagementView();
				itemManagementView.showMenu(this);
			}
			case 2 -> {
				OrderManagementView orderManagementView = new OrderManagementView();
				orderManagementView.showMenu(this);
			}
			case 3 -> {
				PaymentManagementView paymentManagementView = new PaymentManagementView();
				paymentManagementView.showMenu(this);
			}
			case 0 -> exit(1);
		}
	}
}
