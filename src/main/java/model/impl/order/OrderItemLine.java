package model.impl.order;

import model.impl.item.InventoryItem;

public class OrderItemLine {
	private InventoryItem item;
	private Integer quantity;

	public OrderItemLine(InventoryItem item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public InventoryItem getItem() {
		return item;
	}

	public void setItem(InventoryItem item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
