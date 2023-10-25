package model.impl;

import model.enums.ElectronicsType;
import model.enums.ItemCategory;

import java.time.LocalDate;

public class ElectronicsItem extends InventoryItem{

	private ElectronicsType electronicsType;
	private LocalDate dateOfPurchase;
	private Integer warranty;

	public ElectronicsItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Long itemId,
		Integer itemQuantity, ElectronicsType electronicsType, LocalDate dateOfPurchase, Integer warranty) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemId, itemQuantity);
		this.electronicsType = electronicsType;
		this.dateOfPurchase = dateOfPurchase;
		this.warranty = warranty;
	}

	public ElectronicsItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Integer itemQuantity,
		ElectronicsType electronicsType, LocalDate dateOfPurchase, Integer warranty) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemQuantity);
		this.electronicsType = electronicsType;
		this.dateOfPurchase = dateOfPurchase;
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


}
