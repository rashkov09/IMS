package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.gson.GsonFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUnit<T> implements DataPersistence<T> {
	private final String path;
	private final static Gson gson = GsonFactory.getInstance();

	public PersistenceUnit(String path) {
		this.path = path;
	}

	@Override
	public boolean save(List<T> data) {
		String dataToJson = gson.toJson(data);
		try (FileWriter writer = new FileWriter(path)) {
			writer.write(dataToJson);
			return true;
		} catch (IOException e) {
				e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<T> fetchAll(TypeToken<List<T>> typeToken) {
		List<T> list = null;
		try (Reader reader = new FileReader(path)) {
			Type listType =typeToken.getType();
			 list = gson.fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list != null ? list : new ArrayList<>();
	}
}
