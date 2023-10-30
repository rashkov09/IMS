package data.gson.order;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import data.gson.util.CommonExtract;
import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.payment.PaymentMethod;
import model.impl.order.BuyOrder;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.order.SellOrder;
import model.impl.supplier.ItemSupplier;
import model.impl.user.CustomerUser;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		JsonArray itemsArray = jsonObject.getAsJsonArray("orderItems");
		List<OrderItemLine> items = CommonExtract.getListOfOrderItemsFromJson(context, itemsArray);

		switch (orderType) {
			case BUY -> {
				ItemSupplier itemSupplier = context.deserialize(jsonObject.get("supplier"), ItemSupplier.class);
				User user = context.deserialize(jsonObject.get("employee"), EmployeeUser.class);
				return new BuyOrder(items, orderStatus, stampCreated, stampModified, orderId, orderType, paymentMethod,
				                    itemSupplier, user);
			}
			case SELL -> {
				User user = context.deserialize(jsonObject.get("customer"), CustomerUser.class);
				return new SellOrder(items,orderStatus,stampCreated,stampModified,orderId,orderType,paymentMethod,user);
			}
			default -> throw new JsonParseException("Error");
		}
	}
}