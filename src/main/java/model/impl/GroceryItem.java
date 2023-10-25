package model.impl;

import model.enums.GroceryType;
import model.enums.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem{

	private GroceryType groceryType;
	private LocalDate expirationDate;

	public GroceryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category, Integer quantity, BigDecimal price) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, quantity, price);
	}

	public GroceryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		BigDecimal price, Long itemId, Integer itemQuantity, GroceryType groceryType, LocalDate expirationDate) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price, itemId, itemQuantity);
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
