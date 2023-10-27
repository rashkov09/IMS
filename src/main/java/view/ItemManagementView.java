package view;

import model.impl.user.User;
import service.ItemService;
import service.impl.ItemServiceImpl;
import util.ConsoleRangeReader;

public class ItemManagementView implements ConsoleView{
	private static final ItemService itemService = new ItemServiceImpl();
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- You are in Item management menu -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Add item
	                                         2. Remove item by ID
	                                         3. Display all items
	                                         4. Search
	                                                                                  
	                                         0. Back
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
			System.out.println(itemService.addItem());
				this.showMenu(invoker,user);
			}
			case 2 -> {
				System.out.println(itemService.removeItem());
				this.showMenu(invoker,user);
			}
			case 3 -> {
				System.out.println(itemService.displayAllItems());
				this.showMenu(invoker,user);
			}
			case 4 -> {
				ItemSearchView itemSearchView = new ItemSearchView();
				itemSearchView.showMenu(this,user);
			}
			case 0 -> invoker.showMenu(this,user);
		}
	}
}
