package model.impl.order;

import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;

public class InventoryOrder extends AbstractOrder {
	private static Long ID_COUNTER=0L;
	private Long orderId;
	private OrderType orderType;
	private PaymentMethod paymentMethod;
	public InventoryOrder() {
		super();
		setOrderId();
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

	private void setOrderId() {
		this.orderId = ++ID_COUNTER;
	}

}
