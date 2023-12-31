package model.impl.item;

import model.enums.item.ItemCategory;
import model.iface.Categorizable;
import model.iface.Item;
import model.iface.Sellable;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item, Sellable, Categorizable {

	private String itemName;
	private String itemManufacturer;
	private String itemCountryOfOrigin;
	private String itemDescription;
	private ItemCategory itemCategory;
	private BigDecimal itemPrice;

	public AbstractItem() {
	}

	public AbstractItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, ItemCategory category, BigDecimal price) {
		this.itemName = itemName;
		this.itemManufacturer = itemManufacturer;
		this.itemCountryOfOrigin = itemCountryOfOrigin;
		this.itemDescription = itemDescription;
		this.itemCategory = category;
		this.itemPrice = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemManufacturer() {
		return itemManufacturer;
	}

	public void setItemManufacturer(String itemManufacturer) {
		this.itemManufacturer = itemManufacturer;
	}

	public String getItemCountryOfOrigin() {
		return itemCountryOfOrigin;
	}

	public void setItemCountryOfOrigin(String itemCountryOfOrigin) {
		this.itemCountryOfOrigin = itemCountryOfOrigin;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Override
	public String getItemDetails() {
		return String.format("Item name: %s\nManufactured by: %s\nCountry of origin: %s", this.getItemName()
			, this.getItemManufacturer(), this.getItemCountryOfOrigin());
	}

	@Override
	public String displayItemDescription() {
		return this.getItemDescription();
	}


	@Override
	public ItemCategory getItemCategory() {
		return this.itemCategory;
	}

	@Override
	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	@Override
	public BigDecimal calculateItemValue() {
		return BigDecimal.ZERO;
	}


	@Override
	public BigDecimal setItemPrice(BigDecimal price) {
		return this.itemPrice = price;
	}

	@Override
	public BigDecimal getItemPrice() {
		return this.itemPrice;
	}
}
