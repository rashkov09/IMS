package model.impl.order;

import model.enums.order.OrderStatus;
import model.iface.Order;
import model.impl.item.InventoryItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractOrder implements Order {

	private Map<InventoryItem, Integer> orderItems;
	private OrderStatus orderStatus;
	private LocalDateTime stampCreated;
	private LocalDateTime stampModified;

	public AbstractOrder() {
		this.orderItems = new LinkedHashMap<>();
		this.orderStatus = OrderStatus.CREATED;
		this.stampCreated = LocalDateTime.now();
		this.stampModified = null;
	}

	public Map<InventoryItem, Integer> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Map<InventoryItem, Integer> orderItems) {
		this.orderItems = orderItems;
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

	public void setStampCreated(LocalDateTime stampCreated) {
		this.stampCreated = stampCreated;
	}

	public LocalDateTime getStampModified() {
		return stampModified;
	}

	public void setStampModified(LocalDateTime stampModified) {
		this.stampModified = stampModified;
	}

	@Override
	public void addItem(InventoryItem item, Integer quantity) {
		if (this.orderItems.containsKey(item)) {
			this.orderItems.put(item, this.orderItems.get(item) + quantity);
		} else {
			this.orderItems.put(item, quantity);
		}
	}

	@Override
	public BigDecimal getTotalOrderAmount() {
		return this.orderItems.entrySet()
		                      .stream()
		                      .map(entry -> entry.getKey().getItemPrice().multiply(BigDecimal.valueOf(entry.getValue())))
		                      .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String printOrder() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Order created: ").append(this.getStampCreated())
			.append("\t\t")
			.append("Order modified: ").append(this.getStampModified() != null ? this.getStampModified() : "never")
			.append(System.lineSeparator())
			.append("Order status: ").append(this.getOrderStatus().name()).append(System.lineSeparator());
		this.getOrderItems().forEach((key, value) -> {
			builder.append("Item: ").append(key.displayItemDescription()).append("\t\t").append("Qty: ").append(value).append("\t\t")
			       .append("TOTAL: ").append(key.getItemPrice().multiply(BigDecimal.valueOf(value)))
			       .append(System.lineSeparator());
		});
		return builder.toString();
	}


}
