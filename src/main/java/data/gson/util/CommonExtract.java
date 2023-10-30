package data.gson.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.impl.item.InventoryItem;
import model.impl.order.OrderItemLine;

import java.util.ArrayList;
import java.util.List;

public class CommonExtract {
	public static List<OrderItemLine> getListOfOrderItemsFromJson(
		JsonDeserializationContext jsonDeserializationContext, JsonArray itemsArray) {
		List<OrderItemLine> items = new ArrayList<>();
		for (JsonElement itemElement : itemsArray) {
			JsonObject itemObject = itemElement.getAsJsonObject();
			InventoryItem item = jsonDeserializationContext.deserialize(itemObject.get("item"), InventoryItem.class);
			int quantity = itemObject.get("quantity").getAsInt();
			items.add(new OrderItemLine(item, quantity));
		}
		return items;
	}
}
