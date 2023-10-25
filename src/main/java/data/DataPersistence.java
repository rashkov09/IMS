package data;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public interface DataPersistence<T> {
	boolean save(List<T> data);
	List<T> fetchAll(TypeToken<List<T>> typeToken);

}
