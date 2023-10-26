package data.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.enums.ClothingSexCategory;
import model.enums.ClothingSize;
import model.enums.ClothingType;
import model.enums.ElectronicsType;
import model.enums.FurnitureType;
import model.enums.GroceryType;
import model.enums.ItemCategory;
import model.impl.ClothingItem;
import model.impl.ElectronicsItem;
import model.impl.FurnitureItem;
import model.impl.GroceryItem;
import model.impl.InventoryItem;
import util.DateParser;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemDeserializer implements JsonDeserializer<InventoryItem> {

	@Override
	public InventoryItem deserialize(
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

		switch (category) {
			case ELECTRONICS -> {
				ElectronicsType electronicsType = ElectronicsType.valueOf(jsonObject.get("electronicsType").getAsString());
				int warranty = jsonObject.get("warranty").getAsInt();

				return new ElectronicsItem(id, name, manufacturer, country, description, category, price, quantity,
				                           electronicsType,
				                           warranty);
			}
			case FOOD_GROCERIES -> {
				GroceryType groceryType = GroceryType.valueOf(jsonObject.get("groceryType").getAsString());
				LocalDate expirationDate = DateParser.parseFromString(jsonObject.get("expirationDate").getAsString());
				return new GroceryItem(id, name, manufacturer, country, description, category, price, quantity, groceryType,
				                       expirationDate);
			}
			case CLOTHING_APPAREL -> {
				ClothingType clothingType = ClothingType.valueOf(jsonObject.get("clothingType").getAsString());
				ClothingSexCategory clothingSexCategory = ClothingSexCategory.valueOf(jsonObject.get("clothingSexCategory").getAsString());
				ClothingSize clothingSize = ClothingSize.valueOf(jsonObject.get("clothingSize").getAsString());
				return  new ClothingItem(id, name, manufacturer,country,description,category,price,quantity,clothingType,clothingSexCategory,clothingSize);
			}
			case FURNITURE -> {
				FurnitureType furnitureType = FurnitureType.valueOf(jsonObject.get("furnitureType").getAsString());
				BigDecimal deliveryPrice = BigDecimal.valueOf(Double.parseDouble(jsonObject.get("deliveryPrice").getAsString()));
				return new FurnitureItem(id,name,manufacturer,country,description,category,price,quantity,furnitureType,deliveryPrice);
			}
			default -> throw new JsonParseException("Error");
		}
	}
}
