package data;

import java.util.List;

public interface Data<T> {

	T getById(Long id);
	boolean add(T object);
	boolean removeById(Long id);
	Long getLastId();
	List<T> getAll();
}
