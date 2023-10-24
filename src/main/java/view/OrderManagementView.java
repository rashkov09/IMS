package view;

import service.ItemService;
import service.OrderService;
import service.impl.ItemServiceImpl;
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
	public void showMenu(ConsoleView invoker) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(orderService.addOrder());
				this.showMenu(invoker);
			}
			case 2 -> {
				System.out.println(orderService.removeOrderById());
				this.showMenu(invoker);
			}
			case 3 -> {
				System.out.println(orderService.displayAllOrders());
				this.showMenu(invoker);
			}
			case 4 -> {
				// TODO search orders
				this.showMenu(invoker);
			}
			case 0 -> invoker.showMenu(this);
		}
	}
}
