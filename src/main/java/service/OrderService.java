package service;

import model.impl.order.InventoryOrder;
import model.impl.user.User;

public interface OrderService {

	InventoryOrder addOrder(User user);
	String removeOrderById();
	String displayAllOrders();

}
