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
