package data.gson.order;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.impl.order.InventoryOrder;

public class OrderSerializer implements JsonSerializer<InventoryOrder> {

	@Override
	public JsonElement serialize(InventoryOrder order, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("orderStatus", order.getOrderStatus().toString());
		jsonObject.addProperty("stampCreated", order.getStampCreated().toString());
		jsonObject.addProperty("stampModified", order.getStampModified().toString());

			jsonObject.addProperty("orderId", order.getOrderId());
			jsonObject.addProperty("orderType", order.getOrderType().toString());
			jsonObject.addProperty("paymentMethod", order.getPaymentMethod().toString());

		return jsonObject;
	}
}