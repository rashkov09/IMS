package service;

import model.impl.item.InventoryItem;

public interface ItemService {
	String addItem();

	String removeItem();

	String displayAllItems();

	String searchById();
	String searchByName();

	String searchByType();

	InventoryItem getItemById(long itemId);
}
