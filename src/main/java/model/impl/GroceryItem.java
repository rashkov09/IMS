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
		ItemCategory category,BigDecimal price,Integer quantity, GroceryType groceryType, LocalDate expirationDate) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, quantity, price);
		this.groceryType = groceryType;
		this.expirationDate = expirationDate;
	}

	public GroceryItem(
		Long itemId, String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		BigDecimal price, Integer itemQuantity, GroceryType groceryType, LocalDate expirationDate) {
		super( itemId, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price, itemQuantity);
		this.groceryType = groceryType;
		this.expirationDate = expirationDate;
	}

	public GroceryType getGroceryType() {
		return groceryType;
	}

	public void setGroceryType(GroceryType groceryType) {
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

	@Override
	public String getItemDetails() {
		return String.format("""
		                       Item ID: %d
		                       Item name: %s
		                       Manufacturer: %s
		                       Manufactured in: %s
		                       Description: %s
		                       Category: %s
		                       Type: %s
		                       Quantity: %d
		                       Price: %.2f
		                       Good before: %s
		                     """, this.getItemId(), this.getItemName(), this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(), this.getItemDescription(), this.getItemCategory(),
		                     this.getGroceryType().getTypeName()
			, this.getItemQuantity(), this.getItemPrice(), this.getExpirationDate().toString());
	}
}
