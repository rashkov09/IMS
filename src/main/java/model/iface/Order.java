package model.iface;

import model.impl.item.InventoryItem;

import java.math.BigDecimal;

public interface Order {

	void addItem(InventoryItem item, Integer quantity);

	String printOrder();

	BigDecimal getTotalOrderAmount();

}
