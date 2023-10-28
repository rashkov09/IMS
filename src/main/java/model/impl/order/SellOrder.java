package model.impl.order;

import model.enums.order.OrderType;
import model.iface.Processable;
import model.impl.user.CustomerUser;
import model.impl.user.User;

public class SellOrder extends InventoryOrder implements Processable {
	private User customer;

	public SellOrder(Long orderId,CustomerUser customer) {
		super(orderId);
		this.setOrderType(OrderType.SELL);
		this.customer = customer;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	@Override
	public void process() {

	}
}
