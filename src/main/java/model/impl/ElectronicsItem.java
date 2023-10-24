package model.impl;

import model.enums.ElectronicsType;
import model.enums.ItemCategory;

import java.time.LocalDate;

public class ElectronicsItem extends InventoryItem{

	private ElectronicsType electronicsType;
	private LocalDate dateOfPurchase;
	private Integer warranty;

	public ElectronicsItem(
		String itemName, String itemManufacturer, String itemCountryOfOrigin, String itemDescription, Integer itemQuantity, LocalDate dateOfPurchase, Integer warranty, ElectronicsType electronicsType) {
		super(itemName, itemManufacturer, itemCountryOfOrigin, itemDescription, itemQuantity);
		setDateOfPurchase(dateOfPurchase);
		setWarranty(warranty);
		setElectronicsType(electronicsType);
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
