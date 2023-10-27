package model.impl.item;

import model.enums.item.ElectronicsType;
import model.enums.item.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ElectronicsItem extends InventoryItem {

	private ElectronicsType electronicsType;
	private LocalDate dateOfPurchase;
	private Integer warranty;

	public ElectronicsItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		BigDecimal price, Integer itemQuantity, ElectronicsType electronicsType, Integer warranty) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, itemQuantity,price);
		this.electronicsType = electronicsType;
		this.warranty = warranty;
	}

	public ElectronicsItem(
		Long itemId, String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription,
		ItemCategory category,
		BigDecimal price, Integer itemQuantity, ElectronicsType electronicsType,
		Integer warranty) {
		super(itemId,itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, category, price, itemQuantity);
		this.electronicsType = electronicsType;
		this.warranty = warranty;
	}

	public ElectronicsType getElectronicsType() {
		return electronicsType;
	}

	public void setElectronicsType(ElectronicsType electronicsType) {
		this.electronicsType = electronicsType;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	@Override
	public Boolean isBreakable() {
		return true;
	}

	@Override
	public void handleBrokenItem() {
		LocalDate to = this.dateOfPurchase.plusYears(warranty);
		System.out.println("Warranty is valid to: " + to);
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
                       Warranty: %d
                     """,this.getItemId(),this.getItemName(),this.getItemManufacturer(),
		                     this.getItemCountryOfOrigin(),this.getItemDescription(),this.getItemCategory(),this.getElectronicsType().getTypeName()
			,this.getItemQuantity(),this.getItemPrice(),this.getWarranty());
	}

}
