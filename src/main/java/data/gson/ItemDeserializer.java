package data.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.enums.ItemCategory;
import model.iface.Item;
import model.impl.InventoryItem;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class ItemDeserializer implements JsonDeserializer<Item> {

	@Override
	public Item deserialize(
		JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws
		JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		// Extract properties from the JSON

		Long id = jsonObject.get("itemId").getAsLong();
		Integer quantity = jsonObject.get("itemQuantity").getAsInt();
		String name = jsonObject.get("itemName").getAsString();
		String manufacturer = jsonObject.get("itemManufacturer").getAsString();
		String country = jsonObject.get("itemCountryOfOrigin").getAsString();
		String description = jsonObject.get("itemDescription").getAsString();
		ItemCategory category = ItemCategory.valueOf(jsonObject.get("itemCategory").getAsString());
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(jsonObject.get("itemPrice").getAsString()));
		// Create an Item object

		return new InventoryItem(name,manufacturer,country,description,category,price,id,quantity);
	}
}
