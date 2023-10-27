package data.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.gson.date.LocalDateDeserializer;
import data.gson.date.LocalDateSerializer;
import data.gson.date.LocalDateTimeDeserializer;
import data.gson.date.LocalDateTimeSerializer;
import data.gson.item.ItemDeserializer;
import data.gson.item.ItemSerializer;
import data.gson.user.UserDeserializer;
import model.impl.item.InventoryItem;
import model.impl.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class GsonFactory {

	public static Gson getInstance() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(InventoryItem.class, new ItemDeserializer());
		gsonBuilder.registerTypeAdapter(InventoryItem.class, new ItemSerializer());
		gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());
		gsonBuilder.setPrettyPrinting();


		return gsonBuilder.create();
	}
}
