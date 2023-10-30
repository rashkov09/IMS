package model.impl.order;

import model.impl.item.InventoryItem;

import java.math.BigDecimal;

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

	public BigDecimal getTotalLinePrice(){
		return this.getItem().getItemPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
	}
}
