package model.impl;

import model.enums.ItemCategory;

import java.time.LocalDate;

public class GroceryItem extends InventoryItem{
	private LocalDate expirationDate;

	public GroceryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		Integer itemQuantity, LocalDate expirationDate) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemQuantity);
		setItemCategory(ItemCategory.FOOD_GROCERIES);
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
