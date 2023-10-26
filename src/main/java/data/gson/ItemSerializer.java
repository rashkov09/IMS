package data.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.impl.ClothingItem;
import model.impl.ElectronicsItem;
import model.impl.GroceryItem;
import model.impl.InventoryItem;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<InventoryItem> {

	@Override
	public JsonElement serialize(InventoryItem item, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject itemJson = new JsonObject();
		itemJson.addProperty("itemId", item.getItemId());
		itemJson.addProperty("itemName", item.getItemName());
		itemJson.addProperty("itemManufacturer", item.getItemManufacturer());
		itemJson.addProperty("itemCountryOfOrigin", item.getItemCountryOfOrigin());
		itemJson.addProperty("itemDescription", item.getItemDescription());
		itemJson.addProperty("itemCategory", item.getItemCategory());
		itemJson.addProperty("itemQuantity", item.getItemQuantity());
		itemJson.addProperty("itemPrice", item.getItemPrice());

		if (item instanceof ElectronicsItem electronicsItem) {
			itemJson.addProperty("electronicsType",electronicsItem.getElectronicsType().getTypeName());
			itemJson.addProperty("warranty",electronicsItem.getWarranty());
		} else if (item instanceof GroceryItem groceryItem) {
			itemJson.addProperty("groceryType",groceryItem.getGroceryType().getTypeName());
			itemJson.addProperty("expirationDate",groceryItem.getExpirationDate().toString());
		} else if (item instanceof ClothingItem clothingItem) {
			itemJson.addProperty("clothingType",clothingItem.getClothingType().getTypeName());
			itemJson.addProperty("clothingSexCategory",((ClothingItem) item).getClothingSexCategory().getCategoryName());
			itemJson.addProperty("clothingSize",((ClothingItem) item).getClothingSize().getSizeName());
		}
		return itemJson;
	}
}
