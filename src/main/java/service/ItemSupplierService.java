package service;

import model.impl.supplier.ItemSupplier;

public interface ItemSupplierService {

	String addSupplier();

	ItemSupplier getSupplierById(long supplierId);

	String addItemToSupplier(long supplierId, long item);

	void displayAllItems();

	String removeSupplier();

	String displayAllSuppliers();

	String findByItemId(long itemId);

	String displaySuppliersOrderDetails();
}
