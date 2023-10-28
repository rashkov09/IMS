package service.impl;

import data.SupplierData;
import model.enums.payment.PaymentMethod;
import model.impl.item.InventoryItem;
import model.impl.supplier.ItemSupplier;
import service.ItemService;
import service.ItemSupplierService;
import util.ConsoleRangeReader;
import util.ConsoleReader;

import java.time.LocalDate;
import java.util.List;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class ItemSupplierServiceImpl implements ItemSupplierService {

	private static final SupplierData supplierData = new SupplierData();
	private static final ItemService itemService = new ItemServiceImpl();

	@Override
	public String addSupplier() {
		Long supplierId = supplierData.getLastId() + 1;
		System.out.println("Supplier name:");
		String supplierName = ConsoleReader.readString();
		System.out.println("Supplier email:");
		String supplierEmail = ConsoleReader.readString();
		System.out.println("Supplier phone:");
		String supplierPhone = ConsoleReader.readString();
		System.out.println("Supplier address:");
		String supplierAddress = ConsoleReader.readString();
		System.out.println("Supplier contract start date:");
		LocalDate contractStart = ConsoleReader.readDateString();
		System.out.println("Supplier contract end date:");
		LocalDate contractEnd = ConsoleReader.readDateString();
		while (contractEnd.isBefore(contractStart) || contractEnd.isEqual(contractStart)) {
			System.out.println("End date cannot be equal or before start date!");
			contractEnd = ConsoleReader.readDateString();
		}
		System.out.println(PaymentMethod.getAll());
		System.out.println("Please, choose a payment method for this supplier from the list above:");
		int choice = ConsoleRangeReader.readInt(1, PaymentMethod.values().length) - 1;
		PaymentMethod paymentMethod = PaymentMethod.values()[choice];
		ItemSupplier supplier =
			new ItemSupplier(supplierId, supplierName, supplierEmail, supplierPhone, supplierAddress, contractStart,
			                 contractEnd, paymentMethod);
		if (supplierData.add(supplier)) {
			return "Supplier added successfully!";
		}
		return "Failed to add supplier!";
	}

	@Override
	public ItemSupplier getSupplierById(long supplierId) {
		ItemSupplier itemSupplier = supplierData.getById(supplierId);
		if (itemSupplier == null) {
			throw new RuntimeException("Supplier not found!");
		}
		return itemSupplier;
	}

	@Override
	public String addItemToSupplier(long supplierId, long itemId) {
		String result = null;
		try {
			InventoryItem item = itemService.getItemById(itemId);
			if (supplierData.addItemToSupplier(supplierId, item)) {
				result = "Item added to supplier successfully!";
			}
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	@Override
	public void displayAllItems() {
		System.out.println(itemService.displayAllItems());
	}

	@Override
	public String removeSupplier() {
		System.out.println("Please, insert supplier ID:");
		Long supplierId = ConsoleReader.readILong();
		if (supplierData.removeById(supplierId)) {
			return String.format("Supplier with ID %d removed successfully!", supplierId);
		}
		return String.format("Supplier with ID %d not found!", supplierId);
	}

	@Override
	public String displayAllSuppliers() {
		StringBuilder builder = new StringBuilder();
		supplierData.getAll().forEach(
			supplier -> builder.append(supplier.toString()).append(System.lineSeparator()).append(HORIZONTAL_LINE_BREAK));
		return builder.toString().isEmpty() ? "No suppliers found!" : builder.toString();
	}

	@Override
	public String findByItemId(long itemId) {
		StringBuilder builder = new StringBuilder();
		List<ItemSupplier> suppliersWithItem = supplierData.getAll().stream()
		                                                   .filter(supplier -> supplier.getItems().stream()
		                                                                               .anyMatch(item -> item.getItemId()
		                                                                                                     .equals(
			                                                                                                     itemId)))
		                                                   .toList();
		suppliersWithItem.forEach(
			supplier -> builder.append(supplier).append(System.lineSeparator()).append(HORIZONTAL_LINE_BREAK));
		return builder.toString().isEmpty() ? "No suppliers for this item found!" : builder.toString();
	}
}
