package data.gson.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.impl.user.CustomerUser;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject userJson = new JsonObject();
		userJson.addProperty("name",user.getName());
		userJson.addProperty("phone",user.getPhone());
		userJson.addProperty("email",user.getEmail());
		userJson.addProperty("username",user.getUsername());
		userJson.addProperty("password",user.getPassword());
		userJson.addProperty("id",user.getId());
		userJson.addProperty("userRole",user.getUserRole().name());
		if (user instanceof EmployeeUser){
			userJson.addProperty("isAdmin",((EmployeeUser) user).getAdmin());
		} else if (user instanceof CustomerUser){
			userJson.addProperty("orderHistory", new ArrayList<>().toString());
		}
		return userJson;
	}
}
