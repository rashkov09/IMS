package view;

import model.impl.item.InventoryItem;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.user.CustomerUser;
import model.impl.user.User;
import service.ItemService;
import service.OrderService;
import service.UserService;
import service.impl.ItemServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;
import util.ConsoleRangeReader;
import util.ConsoleReader;

public class CustomerView implements ConsoleView {

	private static final UserService userService = new UserServiceImpl();
	private static final ItemService itemService = new ItemServiceImpl();
	private static final OrderService orderService = new OrderServiceImpl();
	private static final int MAX_CHOICE = 7;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
                                            Please, choose an option to continue:
                                            1. Profile
                                            2. Display all products
                                            3. Add item to cart
                                            4. Display items in cart
                                            5. Add order
                                            6. Cancel order
                                            7. Search
	                                                                                  
                                            0. Logout
                                            """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		CustomerUser customer = (CustomerUser) user;
		switch (choice) {
			case 1 -> {
				userService.displayUserProfile(customer);
				this.showMenu(invoker, customer);
			}
			case 2 -> {
				System.out.println(itemService.displayAllItems());
				this.showMenu(invoker, customer);
			}
			case 3 -> {
				System.out.println("Please, insert item ID:");
				long itemId = ConsoleReader.readILong();
				InventoryItem item = itemService.getItemById(itemId);
				System.out.println("Please, insert item quantity:");
				int quantity = ConsoleReader.readInt();
				System.out.println(userService.addItemToCart(customer, new OrderItemLine(item, quantity)));
				this.showMenu(invoker, userService.getUpdatedUser(customer));
			}
			case 4 -> {
				System.out.println(customer.displayItemsInCart());
				this.showMenu(invoker, customer);
			}
			case 5 -> {
				InventoryOrder inventoryOrder = orderService.addOrder(customer);
				if (inventoryOrder != null) {
					userService.addOrderToHistory(customer, inventoryOrder);
				}
				this.showMenu(this, userService.getUpdatedUser(customer));
			}
			case 6 -> {
				if (customer.displayOrderHistory().isEmpty()) {
					System.out.println("You do not have any active orders!");
					this.showMenu(invoker, customer);
					break;
				}
				System.out.println(customer.displayOrderHistory());
				System.out.println("Please, insert order ID to cancel:");
				long orderId = ConsoleReader.readILong();
				if (orderService.cancelOrder(orderId)) {
					userService.updateOrderHistory(customer, orderId);
					System.out.printf("Order with id %d is canceled!\n", orderId);
				} else {
					System.out.println("Order cancellation failed!");
				}
				this.showMenu(this, userService.getUpdatedUser(customer));
			}
			case 7 -> {
				ItemSearchView itemSearchView = new ItemSearchView();
				itemSearchView.showMenu(this, customer);
			}
		}
	}
}
