package data.gson.order;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;
import model.iface.Item;
import model.impl.item.InventoryItem;
import model.impl.order.BuyOrder;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.supplier.ItemSupplier;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDeserializer implements JsonDeserializer<InventoryOrder> {

	@Override
	public InventoryOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
		JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		OrderStatus orderStatus = OrderStatus.valueOf(jsonObject.get("orderStatus").getAsString());
		String stampCreatedValue = jsonObject.get("stampCreated").getAsString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime stampCreated = LocalDateTime.parse(stampCreatedValue, formatter);
		String stampModifiedValue = jsonObject.get("stampModified").getAsString();
		LocalDateTime stampModified = LocalDateTime.parse(stampModifiedValue, formatter);
		Long orderId = jsonObject.get("orderId").getAsLong();
		OrderType orderType = OrderType.valueOf(jsonObject.get("orderType").getAsString());
		PaymentMethod paymentMethod = PaymentMethod.valueOf(jsonObject.get("paymentMethod").getAsString());
		List<OrderItemLine> items = new ArrayList<>();
		JsonArray itemsArray = jsonObject.getAsJsonArray("orderItems");
		for (JsonElement itemElement : itemsArray) {
			JsonObject itemObject = itemElement.getAsJsonObject();
			InventoryItem item = context.deserialize(itemObject.get("item"), InventoryItem.class);
			int quantity = itemObject.get("quantity").getAsInt();
			items.add(new OrderItemLine(item, quantity));
		}

		switch (orderType) {
			case BUY -> {
				ItemSupplier itemSupplier = context.deserialize(jsonObject.get("supplier"), ItemSupplier.class);
				User user = context.deserialize(jsonObject.get("employee"), EmployeeUser.class);
				return new BuyOrder(items, orderStatus, stampCreated, stampModified, orderId, orderType, paymentMethod,
				                    itemSupplier, user);
			}
			case SELL -> {
				return null;
			}
			default -> throw new JsonParseException("Error");
		}
	}
}