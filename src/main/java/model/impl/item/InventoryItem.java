package model.impl.item;

import model.enums.item.ItemCategory;

import java.math.BigDecimal;

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


	public Long getItemId() {
		return itemId;
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
