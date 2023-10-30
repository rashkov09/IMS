package data.gson.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.user.CustomerUser;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject userJson = new JsonObject();
		userJson.addProperty("name", user.getName());
		userJson.addProperty("phone", user.getPhone());
		userJson.addProperty("email", user.getEmail());
		userJson.addProperty("username", user.getUsername());
		userJson.addProperty("password", user.getPassword());
		userJson.addProperty("id", user.getId());
		userJson.addProperty("userRole", user.getUserRole().name());
		if (user instanceof EmployeeUser) {
			userJson.addProperty("isAdmin", ((EmployeeUser) user).getAdmin());
		} else if (user instanceof CustomerUser) {
			JsonArray orderHistoryJsonArray = new JsonArray();
			for (InventoryOrder order : ((CustomerUser) user).getOrderHistory()) {
				JsonElement orderJson = jsonSerializationContext.serialize(order, InventoryOrder.class);
				orderHistoryJsonArray.add(orderJson);
			}

			userJson.add("orderHistory", orderHistoryJsonArray);

			JsonArray userCartArray = new JsonArray();
			for (OrderItemLine line : ((CustomerUser) user).getUserCart()) {
				JsonElement lineJson = jsonSerializationContext.serialize(line, OrderItemLine.class);
				userCartArray.add(lineJson);
			}
			userJson.add("userCart", userCartArray);
		}
		return userJson;
	}
}
