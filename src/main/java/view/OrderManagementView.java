package view;

import model.impl.user.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import util.ConsoleRangeReader;

public class OrderManagementView implements ConsoleView{
	private static final OrderService orderService = new OrderServiceImpl();
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- You are in Order management menu -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Add order
	                                         2. Remove order by ID
	                                         3. Display all orders
	                                         4. Search
	                                                                                  
	                                         0. Back
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(orderService.addOrder(user));
				this.showMenu(invoker,user);
			}
			case 2 -> {
				System.out.println(orderService.removeOrderById());
				this.showMenu(invoker,user);
			}
			case 3 -> {
				System.out.println(orderService.displayAllOrders());
				this.showMenu(invoker, user);
			}
			case 4 -> {
				// TODO search orders
				this.showMenu(invoker, user);
			}
			case 0 -> invoker.showMenu(this, user);
		}
	}
}
