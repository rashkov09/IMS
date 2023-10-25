package data;

import com.google.gson.reflect.TypeToken;
import model.iface.Item;

import java.util.List;

import static constant.Shared.ITEM_FILE_PATH;

public class ItemData {

	private static final DataPersistence<Item> itemPersistenceUnit = new PersistenceUnit<>(ITEM_FILE_PATH);

	public boolean addItem(Item item) {
		List<Item> items = itemPersistenceUnit.fetchAll(new TypeToken<>() {
		});
		items.add(item);
		return itemPersistenceUnit.save(items);
	}

	public List<Item> getAllItems() {
		return itemPersistenceUnit.fetchAll(new TypeToken<>() {
		});
	}
}
