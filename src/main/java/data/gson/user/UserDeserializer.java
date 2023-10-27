package data.gson.user;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import model.enums.user.UserRole;
import model.impl.item.InventoryItem;
import model.impl.user.CustomerUser;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserDeserializer implements JsonDeserializer<User> {

	@Override
	public User deserialize(
		JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws
		JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		Long id = jsonObject.get("id").getAsLong();
		String name =jsonObject.get("name").getAsString();
		String phone = jsonObject.get("phone").getAsString();
		String email = jsonObject.get("email").getAsString();
		UserRole userRole = UserRole.valueOf(jsonObject.get("userRole").getAsString());
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		switch (userRole){
			case EMPLOYEE -> {
				Boolean isAdmin = jsonObject.get("isAdmin").getAsBoolean();
				return new EmployeeUser(name,phone,email,username,password,id,userRole,isAdmin  );
			}
			case CUSTOMER -> {
					return new CustomerUser(name,phone,email,username,password,id,userRole,new ArrayList<>());
			}
			default ->  throw new JsonParseException("Error");
		}
	}
}
