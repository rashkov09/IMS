package data.gson.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.impl.item.ClothingItem;
import model.impl.item.ElectronicsItem;
import model.impl.item.FurnitureItem;
import model.impl.item.GroceryItem;
import model.impl.item.InventoryItem;

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
		itemJson.addProperty("itemCategory", item.getItemCategory().name());
		itemJson.addProperty("itemQuantity", item.getItemQuantity());
		itemJson.addProperty("itemPrice", item.getItemPrice());

		if (item instanceof ElectronicsItem electronicsItem) {
			itemJson.addProperty("electronicsType",electronicsItem.getElectronicsType().name());
			itemJson.addProperty("warranty",electronicsItem.getWarranty());
		} else if (item instanceof GroceryItem groceryItem) {
			itemJson.addProperty("groceryType",groceryItem.getGroceryType().name());
			itemJson.addProperty("expirationDate",groceryItem.getExpirationDate().toString());
		} else if (item instanceof ClothingItem clothingItem) {
			itemJson.addProperty("clothingType",clothingItem.getClothingType().name());
			itemJson.addProperty("clothingSexCategory",((ClothingItem) item).getClothingSexCategory().name());
			itemJson.addProperty("clothingSize",((ClothingItem) item).getClothingSize().getSizeName());
		} else if (item instanceof FurnitureItem furnitureItem) {
			itemJson.addProperty("furnitureType",furnitureItem.getFurnitureType().name());
			itemJson.addProperty("deliveryPrice",furnitureItem.getDeliveryPrice());
		}
		return itemJson;
	}
}
