package model.impl.user;

import model.enums.user.UserRole;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;

import java.util.ArrayList;
import java.util.List;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class CustomerUser extends User {

	private  List<OrderItemLine> userCart;
	private  List<InventoryOrder> orderHistory;

	public CustomerUser(
		String name, String phone, String email, String username, String password, Long id, UserRole userRole) {
		super(name, phone, email, username, password, id, userRole);
		this.orderHistory = new ArrayList<>();
		this.userCart = new ArrayList<>();
	}
	public CustomerUser(
		String name, String phone, String email, String username, String password, Long id, UserRole userRole,List<OrderItemLine> userCart, List<InventoryOrder> orderHistory) {
		super(name, phone, email, username, password, id, userRole);
		this.orderHistory = orderHistory;
		this.userCart = userCart;
	}

	public List<InventoryOrder> getOrderHistory() {
		return orderHistory;
	}

	public List<OrderItemLine> getUserCart() {
		return userCart;
	}

	public void setUserCart(List<OrderItemLine> userCart) {
		this.userCart = userCart;
	}

	public String displayItemsInCart() {
		StringBuilder builder = new StringBuilder();
		builder.append("Current Items in Cart:").append(System.lineSeparator());
		this.getUserCart().forEach(
			line -> builder.append(HORIZONTAL_LINE_BREAK).append(String.format("Item: %s", line.getItem().getItemName()))
			               .append("\t\t").append(String.format("Quantity: %d", line.getQuantity())).append("\t\t")
			               .append(String.format("Total: %.2f", line.getTotalLinePrice())).append(System.lineSeparator()));
		return builder.toString();
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Orders: %d\n", this.getOrderHistory().size());
	}

	public void setOrderHistory(List<InventoryOrder> orderHistory) {
		this.orderHistory = orderHistory;
	}
}
