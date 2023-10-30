package service;

import model.impl.user.User;

public interface OrderService {

	String addOrder(User user);
	String removeOrderById();
	String displayAllOrders();

}
