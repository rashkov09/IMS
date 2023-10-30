package data;

import model.impl.order.InventoryOrder;

import java.util.List;

public interface Data<T> {

	T getById(Long id);
	boolean add(T object);
	boolean removeById(Long id);
	Long getLastId();
	List<T> getAll();

	void update(List<T> list);
}
