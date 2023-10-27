package model.impl.order;

import model.enums.order.OrderType;

public class BaseOrder extends AbstractOrder {
	private static Long ID_COUNTER=0L;
	private Long orderId;
	private OrderType orderType;
	public BaseOrder() {
		super();
		setOrderId();
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
