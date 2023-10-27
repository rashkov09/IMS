package data;

public interface Data<T> {

	T getById(Long id);
	boolean add(T object);

}
