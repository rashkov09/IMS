package model.impl.item;

import model.enums.item.FurnitureType;
import model.enums.item.ItemCategory;

import java.math.BigDecimal;

public class FurnitureItem extends InventoryItem{
	private FurnitureType furnitureType;
	private BigDecimal deliveryPrice;

	public FurnitureItem(
		Long itemId, String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category, BigDecimal price, Integer itemQuantity, FurnitureType furnitureType,
		BigDecimal deliveryPrice) {
		super(itemId, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price, itemQuantity);
		this.furnitureType = furnitureType;
		this.deliveryPrice = deliveryPrice;
	}

	public FurnitureItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		Integer quantity, BigDecimal price, FurnitureType furnitureType, BigDecimal deliveryPrice) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, quantity, price);
		this.furnitureType = furnitureType;
		this.deliveryPrice = deliveryPrice;
	}

	public FurnitureType getFurnitureType() {
		return furnitureType;
	}

	public void setFurnitureType(FurnitureType furnitureType) {
		this.furnitureType = furnitureType;
	}

	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
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
                       Delivery price: %.2f
                       Quantity: %d
                       Price: %.2f
                     """,this.getItemId(),this.getItemName(),this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(),this.getItemDescription(),this.getItemCategory(),
												 this.getFurnitureType().getTypeName(),this.getDeliveryPrice(),
		                     this.getItemQuantity(),this.getItemPrice());
	}
}
