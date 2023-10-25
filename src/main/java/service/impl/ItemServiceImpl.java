package service.impl;

import data.ItemData;
import model.enums.ElectronicsType;
import model.enums.GroceryType;
import model.enums.ItemCategory;
import model.impl.ElectronicsItem;
import model.impl.GroceryItem;
import model.impl.InventoryItem;
import service.ItemService;
import util.ConsoleRangeReader;
import util.ConsoleReader;
import util.DateParser;

import java.math.BigDecimal;
import java.util.List;

public class ItemServiceImpl implements ItemService {
	private final static ItemData itemData = new ItemData();

	private static final String ITEM_CATEGORY_CHOICE = "Please, choose item category from the list above:";
	private static final String ITEM_SUBTYPE_CHOICE = "Please, choose item type from the list above:";
	private static final String ITEM_INFORMATION_MESSAGE= "Please, enter item information:";
	private static final List<String> ITEM_COMMON_PARAMS = List.of("name","manufacturer:","country of origin", "description","quantity", "price");
	private static final String ITEM_PARAM_MESSAGE= "Please, enter item %s:\n";


	@Override
	public String addItem() {
		System.out.println(ITEM_CATEGORY_CHOICE);
		System.out.printf(ItemCategory.getAll());
		int categoryChoice = ConsoleRangeReader.readInt(1,ItemCategory.values().length)-1;
		System.out.println(ITEM_INFORMATION_MESSAGE);

		if (itemData.addItem(getItem(categoryChoice))){
			return "Item added successfully!";
		}
		return "Item addition failed!";
	}

	private InventoryItem getItem(int categoryChoice) {
		String itemName=null;
		String itemManufacturer=null;
		String itemCountryOfOrigin=null;
		String itemDescription=null;
		ItemCategory category = ItemCategory.values()[categoryChoice];
		int itemQuantity=0;
		BigDecimal itemPrice=BigDecimal.ZERO;
		for (int i = 0; i < ITEM_COMMON_PARAMS.size(); i++) {
			System.out.printf(ITEM_PARAM_MESSAGE,ITEM_COMMON_PARAMS.get(i));
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
			}catch (Exception e){
				System.out.println(e.getMessage());
				i--;
			}
		}
		switch (categoryChoice){
			case 0 -> {
				System.out.println(ElectronicsType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1,ElectronicsType.values().length)-1;
				System.out.printf(ITEM_PARAM_MESSAGE,"warranty");
				int warranty = ConsoleReader.readInt();
				return new ElectronicsItem(itemName,itemManufacturer,itemCountryOfOrigin,itemDescription,category,itemPrice,itemQuantity,ElectronicsType.values()[subtype],warranty);
			}
			case 1 -> {
				System.out.println(GroceryType.getAll());
				System.out.println(ITEM_SUBTYPE_CHOICE);
				int subtype = ConsoleRangeReader.readInt(1,GroceryType.values().length)-1;
				System.out.println("Please, insert expiration date:");
				String expirationDate = ConsoleReader.readString();
				return new GroceryItem(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category,itemPrice, itemQuantity, GroceryType.values()[subtype],
				                       DateParser.parseFromString(expirationDate));
			}
			default -> throw new IllegalStateException("Invalid type");
		}
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
