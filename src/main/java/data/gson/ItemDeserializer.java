package data.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.enums.ElectronicsType;
import model.enums.GroceryType;
import model.enums.ItemCategory;
import model.iface.Item;
import model.impl.ElectronicsItem;
import model.impl.GroceryItem;
import model.impl.InventoryItem;
import util.DateParser;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class ItemDeserializer implements JsonDeserializer<Item> {

	@Override
	public Item deserialize(
		JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws
		JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		// Extract properties from the JSON

		ItemCategory category = ItemCategory.valueOf(jsonObject.get("itemCategory").getAsString());
		Long id = jsonObject.get("itemId").getAsLong();
		Integer quantity = jsonObject.get("itemQuantity").getAsInt();
		String name = jsonObject.get("itemName").getAsString();
		String manufacturer = jsonObject.get("itemManufacturer").getAsString();
		String country = jsonObject.get("itemCountryOfOrigin").getAsString();
		String description = jsonObject.get("itemDescription").getAsString();
		// Create an Item object
		Item newItem;
		switch (category) {
			case ELECTRONICS -> {
				ElectronicsType electronicsType = ElectronicsType.valueOf(jsonObject.get("electronicsType").getAsString());
				LocalDate dateOfPurchase = DateParser.parseFromString(jsonObject.get("dateOfPurchase").getAsString());
				Integer warranty = jsonObject.get("warranty").getAsInt();
				ElectronicsItem item =
					new ElectronicsItem(name, manufacturer, country, description, id, quantity, electronicsType,
					                    dateOfPurchase, warranty);
				item.setItemCategory(category);
				newItem = item;
			}
			case FOOD_GROCERIES -> {
				GroceryType groceryType = GroceryType.valueOf(jsonObject.get("groceryTpe").getAsString());
				LocalDate expirationDate = DateParser.parseFromString(jsonObject.get("expirationDate").toString());
				GroceryItem groceryItem =
					new GroceryItem(name, manufacturer, country, description, id, quantity, groceryType, expirationDate);
				groceryItem.setItemCategory(category);
				newItem = groceryItem;
			}
			default -> {
				InventoryItem item = new InventoryItem(name, manufacturer, country, description, id, quantity);
				item.setItemCategory(category);
				newItem = item;
			}
		}
		// TODO switch for types of Item
		return newItem;
	}
}
