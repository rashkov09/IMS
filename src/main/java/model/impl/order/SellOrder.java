package model.impl.order;

import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;
import model.iface.Processable;
import model.impl.user.CustomerUser;
import model.impl.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class SellOrder extends InventoryOrder implements Processable {
	private User customer;

	public SellOrder(Long orderId,CustomerUser customer) {
		super(orderId);
		this.setOrderType(OrderType.SELL);
		this.customer = customer;
	}

	public SellOrder(
		List<OrderItemLine> orderItems, OrderStatus orderStatus,
		LocalDateTime stampCreated, LocalDateTime stampModified, Long orderId, OrderType orderType,
		PaymentMethod paymentMethod, User customer) {
		super(orderItems, orderStatus, stampCreated, stampModified, orderId, orderType, paymentMethod);
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
