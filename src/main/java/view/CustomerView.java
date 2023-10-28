package view;

import model.impl.user.User;
import service.ItemService;
import service.UserService;
import service.impl.ItemServiceImpl;
import service.impl.UserServiceImpl;
import util.ConsoleRangeReader;

public class CustomerView implements ConsoleView {

	private static final UserService userService = new UserServiceImpl();
	private static final ItemService itemService = new ItemServiceImpl();
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
	                                          Please, choose an option to continue:
	                                          1. Profile
	                                          2. Display all products                                     
	                                          3. Order menu
	                                          4. Search
	                                                                                   
	                                          0. Logout
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				userService.displayUserProfile(user);
				this.showMenu(invoker, user);
			}
			case 2 -> {
				System.out.println(itemService.displayAllItems());
				this.showMenu(invoker, user);
			}
			case 3 -> {
				// TODO add order functionality
			}
			case 4 -> {
				ItemSearchView itemSearchView = new ItemSearchView();
				itemSearchView.showMenu(this, user);
			}
		}
	}
}
