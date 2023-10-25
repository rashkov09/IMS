package model.impl;

import model.enums.GroceryType;

import java.time.LocalDate;

public class GroceryItem extends InventoryItem{

	private GroceryType groceryType;
	private LocalDate expirationDate;

	public GroceryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		Integer itemQuantity, LocalDate expirationDate, GroceryType groceryType) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemQuantity);
		this.expirationDate = expirationDate;
		this.groceryType = groceryType;
	}

	public GroceryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Long itemId,
		Integer itemQuantity, GroceryType groceryType, LocalDate expirationDate) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemId, itemQuantity);
		this.groceryType = groceryType;
		this.expirationDate = expirationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public Boolean isPerishable() {
		return true;
	}

	@Override
	public void handlePerishedItem() {
		this.setItemQuantity(0);
	}
}
