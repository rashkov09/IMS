package service.impl;

import data.ItemData;
import model.enums.item.ClothingSexCategory;
import model.enums.item.ClothingSize;
import model.enums.item.ClothingType;
import model.enums.item.ElectronicsType;
import model.enums.item.FurnitureType;
import model.enums.item.GroceryType;
import model.enums.item.ItemCategory;
import model.iface.Item;
import model.impl.item.ClothingItem;
import model.impl.item.ElectronicsItem;
import model.impl.item.FurnitureItem;
import model.impl.item.GroceryItem;
import model.impl.item.InventoryItem;
import service.ItemService;
import util.ConsoleRangeReader;
import util.ConsoleReader;
import util.DateParser;

import java.math.BigDecimal;
import java.util.List;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class ItemServiceImpl implements ItemService {

	private final static ItemData itemData = new ItemData();

	private static final String ITEM_CATEGORY_CHOICE = "Please, choose item category from the list above:";
	private static final String ITEM_SUBTYPE_CHOICE = "Please, choose item type from the list above:";
	private static final String ITEM_INFORMATION_MESSAGE = "Please, enter item information:";
	private static final List<String> ITEM_COMMON_PARAMS =
		List.of("name", "manufacturer:", "country of origin", "description", "quantity", "price");
	private static final String ITEM_PARAM_MESSAGE = "Please, enter item %s:\n";

	@Override
	public String addItem() {
		System.out.println(ITEM_CATEGORY_CHOICE);
		System.out.printf(ItemCategory.getAll());
		int categoryChoice = ConsoleRangeReader.readInt(1, ItemCategory.values().length) - 1;
		System.out.println(ITEM_INFORMATION_MESSAGE);

		if (itemData.add(getItem(categoryChoice))) {
			return "Item added successfully!";
		}
		return "Item addition failed!";
	}

	private InventoryItem getItem(int categoryChoice) {
		String itemName = null;
		String itemManufacturer = null;
		String itemCountryOfOrigin = null;
		String itemDescription = null;
		ItemCategory category = ItemCategory.values()[categoryChoice];
		int itemQuantity = 0;
		BigDecimal itemPrice = BigDecimal.ZERO;
		Long id = itemData.getLastId() + 1;
		for (int i = 0; i < ITEM_COMMON_PARAMS.size(); i++) {
			System.out.printf(ITEM_PARAM_MESSAGE, ITEM_COMMON_PARAMS.get(i));
			try {
				String input = ConsoleReader.readString();
				switch (i) {
					case 0 -> itemName = input;
					case 1 -> itemManufacturer = input;
					case 2 -> itemCountryOfOrigin = input;
					case 3 -> itemDescription = input;
					case 4 -> itemQuantity = Integer.parseInt(input);
					case 5 -> itemPrice = BigDecimal.valueOf(Double.parseDouble(input));
					default -> throw new IllegalStateException("Unexpected value: " + i);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				i--;
			}
		}
		switch (categoryChoice) {
			case 0 -> {
				System.out.println(ElectronicsType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1, ElectronicsType.values().length) - 1;
				System.out.printf(ITEM_PARAM_MESSAGE, "warranty");
				int warranty = ConsoleReader.readInt();
				return new ElectronicsItem(id, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category,
				                           itemPrice, itemQuantity, ElectronicsType.values()[subtype], warranty);
			}
			case 1 -> {
				System.out.println(GroceryType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1, GroceryType.values().length) - 1;
				System.out.println("Please, insert expiration date:");
				String expirationDate = ConsoleReader.readString();
				return new GroceryItem(id, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category,
				                       itemPrice,
				                       itemQuantity, GroceryType.values()[subtype],
				                       DateParser.parseFromString(expirationDate));
			}
			case 2 -> {
				System.out.println(ClothingType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1, ClothingType.values().length) - 1;
				System.out.println(ClothingSexCategory.getAll());
				System.out.println("Please,choose sex from the list above");
				int sexCategory = ConsoleRangeReader.readInt(1, ClothingSexCategory.values().length) - 1;
				System.out.println(ClothingSize.getAll());
				System.out.println("Please,choose size from the list above");
				int clothingSize = ConsoleRangeReader.readInt(1, ClothingSize.values().length) - 1;

				return new ClothingItem(id, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category,
				                        itemPrice, itemQuantity, ClothingType.values()[subtype],
				                        ClothingSexCategory.values()[sexCategory], ClothingSize.values()[clothingSize]);
			}
			case 3 -> {
				System.out.println(FurnitureType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1, FurnitureType.values().length) - 1;
				System.out.println("Please, enter delivery price for furniture item:");
				BigDecimal deliveryPrice = ConsoleReader.readBigDecimal();
				return new FurnitureItem(id, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category,
				                         itemPrice, itemQuantity, FurnitureType.values()[subtype], deliveryPrice);
			}
			default -> throw new IllegalStateException("Invalid type");
		}
	}

	@Override
	public String removeItem() {
		System.out.printf(ITEM_PARAM_MESSAGE, "ID");
		Long itemId = ConsoleReader.readILong();
		if (itemData.removeById(itemId)) {
			return String.format("Item with ID %d removed successfully!", itemId);
		}
		return String.format("Item with ID %d not found!", itemId);
	}

	@Override
	public String displayAllItems() {
		StringBuilder builder = new StringBuilder();
		itemData.getAll().forEach(
			item -> builder.append(item.getItemDetails()).append(System.lineSeparator()).append(HORIZONTAL_LINE_BREAK)
			               .append(System.lineSeparator()));
		return builder.toString().isEmpty() ? "No items found!\n" : builder.toString();
	}

	@Override
	public String searchById() {
		System.out.printf(ITEM_PARAM_MESSAGE, "ID");
		long itemId = ConsoleReader.readILong();
		Item item = itemData.getById(itemId);
		return item == null ? String.format("Item with ID %d not found!", itemId) : item.getItemDetails();
	}

	@Override
	public String searchByName() {
		System.out.printf(ITEM_PARAM_MESSAGE, "name or part of it");
		String param = ConsoleReader.readString();
		String data = getFilteredDataAsString(itemData.getItemsByName(param));
		return data.isEmpty() ? String.format("No item name contains %s", param) : data;
	}

	@Override
	public String searchByType() {
		System.out.printf(ItemCategory.getAll());
		System.out.println(ITEM_CATEGORY_CHOICE);
		int categoryChoice = ConsoleRangeReader.readInt(1, ItemCategory.values().length) - 1;
		ItemCategory itemCategory = ItemCategory.values()[categoryChoice];
		System.out.println("Do you wish to list all products? (y/n)");
		String all = ConsoleReader.readString();
		String data = "";
		switch (all.toLowerCase()) {
			case "y" -> {
				data = getFilteredDataAsString(itemData.getItemsByCategory(itemCategory.getCategoryName()));
				return data.isEmpty() ? "No items in this category!" : data;
			}
			case "n" -> {
				switch (itemCategory) {
					case ELECTRONICS -> {
						System.out.println(ElectronicsType.getAll());
						System.out.println(ITEM_SUBTYPE_CHOICE);
						int type = ConsoleRangeReader.readInt(1, ElectronicsType.values().length) - 1;
						ElectronicsType electronicsType = ElectronicsType.values()[type];
						data =
							getFilteredDataAsString(itemData.getItemsByCategoryAndType(itemCategory, electronicsType.getTypeName()));
					}
					case FOOD_GROCERIES -> {
						System.out.println(GroceryType.getAll());
						System.out.println(ITEM_SUBTYPE_CHOICE);
						int type = ConsoleRangeReader.readInt(1, GroceryType.values().length) - 1;
						GroceryType groceryType = GroceryType.values()[type];
						data = getFilteredDataAsString(itemData.getItemsByCategoryAndType(itemCategory,
						                                                                  groceryType.getTypeName()));
					}
					case CLOTHING_APPAREL -> {
						System.out.println(ClothingType.getAll());
						System.out.println(ITEM_SUBTYPE_CHOICE);
						int type = ConsoleRangeReader.readInt(1, ClothingType.values().length) - 1;
						ClothingType clothingType = ClothingType.values()[type];
						data = getFilteredDataAsString(itemData.getItemsByCategoryAndType(itemCategory,
						                                                                  clothingType.getTypeName()));
					}
					case FURNITURE -> {
						System.out.println(FurnitureType.getAll());
						System.out.println(ITEM_SUBTYPE_CHOICE);
						int type = ConsoleRangeReader.readInt(1, FurnitureType.values().length) - 1;
						FurnitureType furnitureType = FurnitureType.values()[type];
						data =
							getFilteredDataAsString(itemData.getItemsByCategoryAndType(itemCategory, furnitureType.getTypeName()));
					}
				}
				return data.isEmpty() ? "No items in this category and with this type!\n" : data;
			}
			default -> {
				return "Invalid input!";
			}
		}
	}

	private String getFilteredDataAsString(List<Item> filteredItems) {
		StringBuilder builder = new StringBuilder();
		filteredItems.forEach(item -> builder.append(item.getItemDetails()).append(System.lineSeparator()));
		return builder.toString();
	}
}
