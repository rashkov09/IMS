package view;

import model.impl.user.User;
import util.ConsoleRangeReader;

public class CustomerView implements ConsoleView{
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
	                                          Please, choose an option to continue:
	                                          1. View profile
	                                          2. Show products
	                                          3. Search
	                                          4. Order menu
	                                          5. View basket
	                                                                                    
	                                          0. Logout
	                                          """;
	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
	}
}
