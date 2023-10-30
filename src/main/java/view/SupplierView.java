package view;

import model.impl.user.User;
import service.ItemSupplierService;
import service.impl.ItemSupplierServiceImpl;
import util.ConsoleRangeReader;
import util.ConsoleReader;

public class SupplierView implements ConsoleView {

	private static final ItemSupplierService itemSupplierService = new ItemSupplierServiceImpl();
	private static final int MAX_CHOICE = 6;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
                                            <-- You are in Supplier menu -->
                                            Please, choose an option to continue:
                                            1. Add supplier
                                            2. Add items to supplier
                                            3. Edit supplier
                                            4. Remove supplier
                                            5. Display all suppliers
                                            6. Search supplier by item id
	                                                                                    
                                            0. Back
                                            """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(itemSupplierService.addSupplier());
				this.showMenu(invoker, user);
			}
			case 2 -> {
				System.out.println("Please, insert supplier ID:");
				long supplierId = ConsoleReader.readILong();
				itemSupplierService.displayAllItems();
				System.out.println("Please, insert item ID:");
				long itemId = ConsoleReader.readILong();
				System.out.println(itemSupplierService.addItemToSupplier(supplierId, itemId));
				this.showMenu(invoker,user);
			}
			case 3 -> {
				// TODO add edit supplier functionality
			}
			case 4 -> {
				System.out.println(itemSupplierService.removeSupplier());
				this.showMenu(invoker,user);
			}
			case 5 -> {
				System.out.println(itemSupplierService.displayAllSuppliers());
				this.showMenu(invoker,user);
			}
			case 6 -> {
				System.out.println("Please, insert item ID:");
				long itemId= ConsoleReader.readILong();
				System.out.println(itemSupplierService.findByItemId(itemId));
				this.showMenu(invoker,user);
			}
			case 0 -> {
				invoker.showMenu(new StartVew(),user);
			}
		}
	}
}
