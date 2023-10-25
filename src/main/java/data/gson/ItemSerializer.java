package data.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.iface.Item;
import model.impl.InventoryItem;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<Item> {

	@Override
	public JsonElement serialize(Item item, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject itemJson = new JsonObject();
		InventoryItem inventoryItem = (InventoryItem) item;
		itemJson.addProperty("itemId", inventoryItem.getItemId());
		itemJson.addProperty("itemName", inventoryItem.getItemName());
		itemJson.addProperty("itemManufacturer", inventoryItem.getItemManufacturer());
		itemJson.addProperty("itemCountryOfOrigin", inventoryItem.getItemCountryOfOrigin());
		itemJson.addProperty("itemDescription", inventoryItem.getItemDescription());
		itemJson.addProperty("itemCategory", inventoryItem.getItemCategory());
		itemJson.addProperty("itemQuantity", inventoryItem.getItemQuantity());
		itemJson.addProperty("itemPrice", inventoryItem.getItemPrice());

		return itemJson;
	}
}
