package view;

import model.enums.user.UserRole;
import model.impl.user.EmployeeUser;
import model.impl.user.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ConsoleRangeReader;

import static java.lang.System.exit;

public class StartVew implements ConsoleView {

	private static final UserService userService = new UserServiceImpl();
	private static final int MAX_CHOICE = 2;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
	                                          Please, choose an option to continue:
	                                          1. Login
	                                          2. Register as new customer
	                                                                                    
	                                          0. Exit
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				User loggedUser = userService.login();
				if (loggedUser != null) {
					forwardUserView(loggedUser);
				}
			}
			case 2 -> {
				User registerUser = userService.registerUser();
				if (registerUser != null) {
					forwardUserView(registerUser);
				}
				System.out.println("Registration failed!");
				this.showMenu(null,null);
			}
			case 0 -> exit(1);
		}
	}

	private void forwardUserView(User loggedUser) {
		if (loggedUser.getUserRole().equals(UserRole.EMPLOYEE)) {
			EmployeeUser loggedEmployee = (EmployeeUser) loggedUser;
			if (loggedEmployee.getAdmin()) {
				AdminView adminView = new AdminView();
				adminView.showMenu(this,loggedEmployee);
			}
			EmployeeView employeeView = new EmployeeView();
			employeeView.showMenu(this, loggedEmployee);
		} else {
			CustomerView customerView = new CustomerView();
			customerView.showMenu(this, loggedUser);
		}
		this.showMenu(null, null);
	}
}

