package model.impl.order;

import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryOrder extends AbstractOrder {
	private Long orderId;
	private OrderType orderType;
	private PaymentMethod paymentMethod;
	public InventoryOrder(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public InventoryOrder(
		List<OrderItemLine> orderItems, OrderStatus orderStatus,
		LocalDateTime stampCreated, LocalDateTime stampModified, Long orderId, OrderType orderType,
		PaymentMethod paymentMethod) {
		super(orderItems, orderStatus, stampCreated, stampModified);
		this.orderId = orderId;
		this.orderType = orderType;
		this.paymentMethod = paymentMethod;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId1) {
		this.orderId = orderId1;
	}

	@Override
	public String printOrder() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Order ID :").append(this.getOrderId()).append(System.lineSeparator())
			.append("Order created: ").append(this.getStampCreated())
			.append("\t\t")
			.append("Order modified: ").append(this.getStampModified())
			.append(System.lineSeparator())
			.append("Order status: ").append(this.getOrderStatus().name()).append(System.lineSeparator());
		this.getOrderItems().forEach( line -> {
			builder.append("Item: ").append(line.getItem().displayItemDescription()).append("\t\t").append("Qty: ").append(line.getQuantity()).append("\t\t")
			       .append("TOTAL: ").append(line.getItem().getItemPrice().multiply(BigDecimal.valueOf(line.getQuantity())))
			       .append(System.lineSeparator());
		});
		return builder.toString();
	}

}
