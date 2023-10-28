package model.impl.order;

import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;

public class InventoryOrder extends AbstractOrder {
	private Long orderId;
	private OrderType orderType;
	private PaymentMethod paymentMethod;
	public InventoryOrder(Long orderId) {
		super();
		this.orderId = orderId;
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

}
