package model.impl.item;

import model.enums.item.ClothingSexCategory;
import model.enums.item.ClothingSize;
import model.enums.item.ClothingType;
import model.enums.item.ItemCategory;

import java.math.BigDecimal;

public class ClothingItem extends InventoryItem {

	private final ClothingType clothingType;
	private final ClothingSexCategory clothingSexCategory;
	private final ClothingSize clothingSize;

	public ClothingItem(
		Long itemId, String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category, BigDecimal price, Integer itemQuantity, ClothingType clothingType,
		ClothingSexCategory clothingSexCategory, ClothingSize clothingSize) {
		super(itemId, itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price, itemQuantity);
		this.clothingType = clothingType;
		this.clothingSexCategory = clothingSexCategory;
		this.clothingSize = clothingSize;
	}

	public ClothingType getClothingType() {
		return clothingType;
	}

	public ClothingSexCategory getClothingSexCategory() {
		return clothingSexCategory;
	}

	public ClothingSize getClothingSize() {
		return clothingSize;
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
                          Sex: %s
                          Size: %s
                          Quantity: %d
                          Price: %.2f
                         """, this.getItemId(), this.getItemName(), this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(), this.getItemDescription(), this.getItemCategory(),
		                     this.getClothingType().getTypeName(), this.getClothingSexCategory().getCategoryName(),
		                     this.getClothingSize().toString()
			, this.getItemQuantity(), this.getItemPrice());
	}
}
