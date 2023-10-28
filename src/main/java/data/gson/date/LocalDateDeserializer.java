package data.gson.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(
		JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws
		JsonParseException {
		String dateString = json.getAsString();
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
