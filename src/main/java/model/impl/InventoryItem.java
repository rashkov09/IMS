package model.impl;

import model.AbstractItem;
import model.enums.ItemCategory;

import java.util.Random;

public  class InventoryItem extends AbstractItem {
	private Long itemId;
	private Integer itemQuantity;


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

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}


}
