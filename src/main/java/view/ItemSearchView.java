package view;

import service.ItemService;
import service.impl.ItemServiceImpl;
import util.ConsoleRangeReader;
import util.ConsoleReader;

public class ItemSearchView implements ConsoleView{

	private static final ItemService itemService = new ItemServiceImpl();
	private static final int MAX_CHOICE = 3;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- You are in Item search menu -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Search item by ID
	                                         2. Search item by name
	                                         3. Search item by type
	                                         
	                                                                                  
	                                         0. Back
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				long itemId = ConsoleReader.readInt();
				System.out.println(itemService.searchById(itemId));
				this.showMenu(invoker);
			}
			case 2 -> {
				String param = ConsoleReader.readString();
				System.out.println(itemService.searchByName(param));
				this.showMenu(invoker);
			}
			case 3 -> {
				// TODO decide how to search by type
				this.showMenu(invoker);
			}
			case 0 -> invoker.showMenu(new MainView());
		}
	}
}