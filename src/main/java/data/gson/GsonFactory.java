package data.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.impl.item.InventoryItem;

import java.time.LocalDate;

public final class GsonFactory {

	public static Gson getInstance() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		gsonBuilder.registerTypeAdapter(InventoryItem.class, new ItemDeserializer());
		gsonBuilder.registerTypeAdapter(InventoryItem.class, new ItemSerializer());
		gsonBuilder.setPrettyPrinting();


		return gsonBuilder.create();
	}
}
