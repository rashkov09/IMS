package service;

public interface ItemService {
	String addItem();

	String removeItem();

	String displayAllItems();

	String searchById(long itemId);
	String searchByName(String itemId);
}
