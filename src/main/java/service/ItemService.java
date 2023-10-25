package service;

public interface ItemService {
	String addItem();

	String removeItem();

	String displayAllItems();

	String searchById();
	String searchByName(String itemId);
}
