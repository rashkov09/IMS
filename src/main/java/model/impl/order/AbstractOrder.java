package model.impl.order;

import model.enums.order.OrderStatus;
import model.iface.Order;
import model.impl.item.InventoryItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrder implements Order {

	private final List<OrderItemLine> orderItems;
	private OrderStatus orderStatus;
	private final LocalDateTime stampCreated;
	private LocalDateTime stampModified;

	public AbstractOrder() {
		this.orderItems = new ArrayList<>();
		this.orderStatus = OrderStatus.CREATED;
		this.stampCreated = LocalDateTime.now();
		this.stampModified = LocalDateTime.now();
	}

	public AbstractOrder(
		List<OrderItemLine> orderItems, OrderStatus orderStatus, LocalDateTime stampCreated, LocalDateTime stampModified) {
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
		this.stampCreated = stampCreated;
		this.stampModified = stampModified;
	}

	public List<OrderItemLine> getOrderItems() {
		return orderItems;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getStampCreated() {
		return stampCreated;
	}

	public LocalDateTime getStampModified() {
		return stampModified;
	}

	public void setStampModified(LocalDateTime stampModified) {
		this.stampModified = stampModified;
	}

	@Override
	public void addItem(InventoryItem item, Integer quantity) {
		this.orderItems.add(new OrderItemLine(item,quantity));
	}

	@Override
	public BigDecimal getTotalOrderAmount() {
		return this.orderItems
		                      .stream()
		                      .map(line -> line.getItem().getItemPrice().multiply(BigDecimal.valueOf(line.getQuantity())))
		                      .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
