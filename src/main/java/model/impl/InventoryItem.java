package model.impl;

import model.AbstractItem;
import model.enums.ItemCategory;

import java.math.BigDecimal;
import java.util.Random;

public  class InventoryItem extends AbstractItem {
	private Long itemId;
	private Integer itemQuantity;

	public InventoryItem(
		Long itemId, String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		BigDecimal price, Integer itemQuantity) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price);
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	public InventoryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category, Integer quantity,
		BigDecimal price) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price);
		setItemId();
		this.itemQuantity = quantity;
	}

	public Long getItemId() {
		return itemId;
	}

	private void setItemId() {
		Random random = new Random();
		long min = 0;
		long max = Long.MAX_VALUE;
		this.itemId = random.nextLong(min, max);
	}

	public void setItemId(Long id){
		this.itemId = id;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
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
                       Quantity: %d
                       Price: %.2f
                     """,this.getItemId(),this.getItemName(),this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(),this.getItemDescription(),this.getItemCategory(),this.getItemQuantity(),this.getItemPrice());
	}
}
