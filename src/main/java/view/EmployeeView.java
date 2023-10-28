package view;

import model.impl.user.User;
import util.ConsoleRangeReader;

import static java.lang.System.exit;

public class EmployeeView implements ConsoleView {

	private static final int MAX_CHOICE = 3;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- Welcome to Inventory Management System -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Item Management
	                                         2. Order Management
	                                         3. Payment Management
	                                                                                  
	                                         0. Logout
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				ItemManagementView itemManagementView = new ItemManagementView();
				itemManagementView.showMenu(this,user);
			}
			case 2 -> {
				OrderManagementView orderManagementView = new OrderManagementView();
				orderManagementView.showMenu(this,user);
			}
			case 3 -> {
				PaymentManagementView paymentManagementView = new PaymentManagementView();
				paymentManagementView.showMenu(this,user);
			}
			case 0 -> new StartVew().showMenu(null,null);
		}
	}
}
