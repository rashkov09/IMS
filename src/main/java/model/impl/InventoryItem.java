package model.impl;

import model.AbstractItem;
import model.enums.ItemCategory;

import java.util.Random;

public  class InventoryItem extends AbstractItem {
	private Long itemId;
	private Integer itemQuantity;

	public InventoryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Long itemId,
		Integer itemQuantity) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription);
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	public InventoryItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Integer itemQuantity) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription);
		super.setItemCategory(ItemCategory.NOT_CATEGORIZED);
		setItemId();
		setItemQuantity(itemQuantity);
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

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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
                       Quantity: %d
		                     """,this.getItemId(),this.getItemName(),this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(),this.getItemDescription(),this.getItemQuantity());
	}
}
