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

public class ItemData implements Data<InventoryItem> {

	private static final DataPersistence<InventoryItem> itemPersistenceUnit = new PersistenceUnit<>(ITEM_FILE_PATH);
	private static final TypeToken<List<InventoryItem>> typeToken = new TypeToken<>() {
	};

	@Override
	public boolean add(InventoryItem item) {
		List<InventoryItem> items = itemPersistenceUnit.fetchAll(typeToken);
		items.add(item);
		return itemPersistenceUnit.save(items);
	}

	@Override
	public InventoryItem getById(Long id) {
		return itemPersistenceUnit.fetchAll(typeToken).stream()
		                          .filter(inventoryItem -> inventoryItem.getItemId().equals(id)).findFirst()
		                          .orElse(null);
	}

	@Override
	public Long getLastId() {
		return itemPersistenceUnit.fetchAll(typeToken).stream().map(InventoryItem::getItemId).max(Long::compareTo)
		                          .orElse(0L);
	}

	@Override
	public List<InventoryItem> getAll() {
		return itemPersistenceUnit.fetchAll(typeToken);
	}

	@Override
	public boolean removeById(Long itemId) {
		List<InventoryItem> items = itemPersistenceUnit.fetchAll(typeToken);
		if (items.removeIf(inventoryItem -> inventoryItem.getItemId().equals(itemId))) {
			itemPersistenceUnit.save(items);
			return true;
		}
		return false;
	}

	public List<Item> getItemsByName(String param) {
		return itemPersistenceUnit.fetchAll(typeToken).stream()
		                          .filter(inventoryItem -> inventoryItem.getItemName().toLowerCase()
		                                                                .contains(param.toLowerCase()))
		                          .collect(
			                          Collectors.toList());
	}

	public List<Item> getItemsByCategory(String categoryName) {
		return itemPersistenceUnit.fetchAll(typeToken).stream()
		                          .filter(
			                          inventoryItem -> inventoryItem.getItemCategory().getCategoryName().equals(categoryName))
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

