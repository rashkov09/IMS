package model.impl.user;

import model.enums.user.UserRole;
import model.impl.order.InventoryOrder;

import java.util.ArrayList;
import java.util.List;

public class CustomerUser extends User{
	private final List<InventoryOrder> orderHistory;

	public CustomerUser(
		String name, String phone, String email, String username, String password, Long id, UserRole userRole,
		List<InventoryOrder> orderHistory) {
		super(name, phone, email, username, password, id, userRole);
		this.orderHistory = new ArrayList<>();
	}

	public List<InventoryOrder> getOrderHistory() {
		return orderHistory;
	}
}
