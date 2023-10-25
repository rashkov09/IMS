package data;

import com.google.gson.reflect.TypeToken;
import model.iface.Item;
import model.impl.InventoryItem;

import java.util.List;
import java.util.stream.Collectors;

import static constant.Shared.ITEM_FILE_PATH;

public class ItemData {

	private static final DataPersistence<InventoryItem> itemPersistenceUnit = new PersistenceUnit<>(ITEM_FILE_PATH);

	public boolean addItem(InventoryItem item) {
		List<InventoryItem> items = itemPersistenceUnit.fetchAll(new TypeToken<>() {
		});
		items.add(item);
		return itemPersistenceUnit.save(items);
	}

	public List<InventoryItem> getAllItems() {
		return itemPersistenceUnit.fetchAll(new TypeToken<>() {
		});
	}

	public boolean removeItem(Long itemId) {
		List<InventoryItem> items = itemPersistenceUnit.fetchAll(new TypeToken<>() {
		});
		if (items.removeIf(inventoryItem -> inventoryItem.getItemId().equals(itemId))) {
			itemPersistenceUnit.save(items);
			return true;
		}
		return false;
	}

	public Item getItemById(long itemId) {
		return this.getAllItems().stream().filter(inventoryItem -> inventoryItem.getItemId().equals(itemId)).findFirst().orElse(null);
	}

	public List<Item> getItemsByName(String param) {
		return this.getAllItems().stream().filter(inventoryItem -> inventoryItem.getItemName().toLowerCase().contains(param.toLowerCase())).collect(
			Collectors.toList());
	}
}

