package service;

import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.user.CustomerUser;
import model.impl.user.User;

public interface UserService {

	User login();

	User registerUser();

	String addEmployeeUser();

	String removeEmployeeUser();

	String displayAllUsers();

	String modifyAdminStatus();

	void displayUserProfile(User user);

	String addItemToCart(User user, OrderItemLine item);

	User getUpdatedUser(User user);

	void addOrderToHistory(User user, InventoryOrder inventoryOrder);

	void updateOrderHistory(CustomerUser customer, long orderId);
}
