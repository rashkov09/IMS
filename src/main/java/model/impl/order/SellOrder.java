package model.impl.order;

import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;
import model.iface.Processable;
import model.impl.user.CustomerUser;
import model.impl.user.User;

import java.math.BigDecimal;
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
	public String printOrder() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Order ID :").append(this.getOrderId()).append(System.lineSeparator())
			.append("Customer ID: ").append(this.getCustomer().getId()).append(System.lineSeparator())
			.append("Order created: ").append(this.getStampCreated())
			.append("\t\t")
			.append("Order modified: ").append(this.getStampModified())
			.append(System.lineSeparator())
			.append("Order status: ").append(this.getOrderStatus().name()).append(System.lineSeparator());
		this.getOrderItems().forEach(
			line -> builder.append("Item: ").append(line.getItem().displayItemDescription()).append("\t\t").append("Qty: ")
			               .append(line.getQuantity()).append("\t\t")
			               .append("TOTAL: ").append(line.getItem().getItemPrice().multiply(
					BigDecimal.valueOf(line.getQuantity())))
			               .append(System.lineSeparator()));
		builder.append("ORDER TOTAL: ").append(this.getTotalOrderAmount());
		return builder.toString();
	}

	@Override
	public void process() {

	}
}
