package data;

import com.google.gson.reflect.TypeToken;
import model.enums.item.ItemCategory;
import model.iface.Item;
import model.impl.item.ClothingItem;
import model.impl.item.ElectronicsItem;
import model.impl.item.FurnitureItem;
import model.impl.item.GroceryItem;
import model.impl.item.InventoryItem;

import java.util.ArrayList;
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
		return this.getAllItems().stream().filter(inventoryItem -> inventoryItem.getItemId().equals(itemId)).findFirst()
		           .orElse(null);
	}

	public List<Item> getItemsByName(String param) {
		return this.getAllItems().stream()
		           .filter(inventoryItem -> inventoryItem.getItemName().toLowerCase().contains(param.toLowerCase()))
		           .collect(
			           Collectors.toList());
	}

	public List<Item> getItemsByCategory(String categoryName) {
		return this.getAllItems().stream().filter(inventoryItem -> inventoryItem.getItemCategory().getCategoryName().equals(categoryName))
		           .collect(
			           Collectors.toList());
	}

	public List<Item> getItemsByCategoryAndType(ItemCategory category, String typeName) {
		switch (category) {
			case ELECTRONICS -> {
				return this.getItemsByCategory(category.getCategoryName()).stream().map(item -> (ElectronicsItem) item)
				           .filter(item -> item.getElectronicsType().getTypeName().equals(typeName)).collect(
						Collectors.toList());
			}
			case FOOD_GROCERIES -> {
				return this.getItemsByCategory(category.getCategoryName()).stream().map(item -> (GroceryItem) item)
				           .filter(item -> item.getGroceryType().getTypeName().equals(typeName)).collect(
						Collectors.toList());
			}
			case CLOTHING_APPAREL -> {
				return this.getItemsByCategory(category.getCategoryName()).stream().map(item -> (ClothingItem) item)
				           .filter(item -> item.getClothingType().getTypeName().equals(typeName)).collect(
						Collectors.toList());
			}
			case FURNITURE -> {
				return this.getItemsByCategory(category.getCategoryName()).stream().map(item -> (FurnitureItem) item)
				           .filter(item -> item.getFurnitureType().getTypeName().equals(typeName)).collect(
						Collectors.toList());
			}
			default -> {
				return new ArrayList<>();
			}
		}
	}
}

