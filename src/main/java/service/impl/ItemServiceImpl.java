package service.impl;

import data.ItemData;
import model.iface.Item;
import model.impl.InventoryItem;
import service.ItemService;

public class ItemServiceImpl implements ItemService {
	private final static ItemData itemData = new ItemData();

	@Override
	public String addItem() {
		return  null;
	}

	@Override
	public String removeItem() {
		return null;
	}

	@Override
	public String displayAllItems() {
		StringBuilder builder = new StringBuilder();
		itemData.getAllItems().forEach(item -> builder.append(item.getItemDetails()).append(System.lineSeparator()) );
		return builder.toString();
	}

	@Override
	public String searchById(long itemId) {
		return null;
	}

	@Override
	public String searchByName(String name) {
		return null;
	}

}
