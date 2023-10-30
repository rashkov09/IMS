package model.impl.item;

import model.enums.item.GroceryType;
import model.enums.item.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem{

	private final GroceryType groceryType;
	private LocalDate expirationDate;


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

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
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
