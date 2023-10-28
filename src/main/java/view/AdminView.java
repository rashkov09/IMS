package view;

import model.impl.user.EmployeeUser;
import model.impl.user.User;
import service.ItemSupplierService;
import service.UserService;
import service.impl.ItemSupplierServiceImpl;
import service.impl.UserServiceImpl;
import util.ConsoleRangeReader;

public class AdminView implements ConsoleView {

	private static final UserService userService = new UserServiceImpl();
	private static final ItemSupplierService itemSupplierService = new ItemSupplierServiceImpl();
	private static final int MAX_CHOICE = 7;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
                                           <-- You are in ADMIN menu --> 
	                                          Please, choose an option to continue:
	                                          1. Add employee
	                                          2. Remove employee
	                                          3. Activate/Deactivate ADMIN
	                                          4. Display all users
	                                          5. Add supplier
	                                          6. Edit supplier
	                                          7. Go to management menu
	                                                                                    
	                                          0. Logout
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(userService.addEmployeeUser());
				this.showMenu(invoker, user);
			}
			case 2 -> {
				System.out.println(userService.removeEmployeeUser());
				this.showMenu(invoker, user);
			}
			case 3 -> {
				System.out.println(userService.modifyAdminStatus());
				this.showMenu(invoker,user);
			}
			case 4 -> {
				System.out.println(userService.displayAllUsers());
				this.showMenu(invoker,user);
			}
			case 5 -> {
				System.out.println(itemSupplierService.addSupplier());
				this.showMenu(invoker, user);
			}
			case 7 -> {
				EmployeeView employeeView = new EmployeeView();
				employeeView.showMenu(this,user);
			}
			case 0 -> new StartVew().showMenu(null,null);
		}
	}
}
