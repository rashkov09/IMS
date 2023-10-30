package model.impl.supplier;

import model.enums.payment.PaymentMethod;
import model.impl.item.InventoryItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class ItemSupplier {

	private final List<InventoryItem> items;
	private final Long supplierId;
	private String supplierName;
	private String supplierEmail;
	private String supplierPhone;
	private String supplierAddress;
	private LocalDate contractStart;
	private LocalDate contractEnd;
	private PaymentMethod paymentMethod;

	public ItemSupplier(
		Long supplierId, String supplierName, String supplierEmail, String supplierPhone, String supplierAddress,
		LocalDate contractStart,
		LocalDate contractEnd, PaymentMethod paymentMethod) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhone = supplierPhone;
		this.supplierAddress = supplierAddress;
		this.contractStart = contractStart;
		this.contractEnd = contractEnd;
		this.paymentMethod = paymentMethod;
		this.items = new ArrayList<>();
	}

	public Long getSupplierId() {
		return supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public LocalDate getContractStart() {
		return contractStart;
	}

	public void setContractStart(LocalDate contractStart) {
		this.contractStart = contractStart;
	}

	public LocalDate getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(LocalDate contractEnd) {
		this.contractEnd = contractEnd;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<InventoryItem> getItems() {
		return items;
	}

	public ItemSupplier addItem(InventoryItem item) {
		this.items.add(item);
		return this;
	}

	public String getSupplierDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("""
                                    Supplier ID: %d
                                    Supplier name: %s
                                    Supplier email: %s
                                    Supplier phone: %s
                                    Supplier address: %s
                                     Contract start date: %s
                                    Contract end date: %s
                                    Payment method: %s
                                    Available items:
		                              	
                                 """, this.getSupplierId(), this.getSupplierName(), this.getSupplierEmail(),
		                             this.getSupplierPhone(),
		                             this.getSupplierAddress(), this.getContractStart().toString(),
		                             this.getContractEnd().toString(), this.getPaymentMethod()));
		this.getItems()
		    .forEach(item -> builder.append(HORIZONTAL_LINE_BREAK).append(item.getItemDetails()).append(System.lineSeparator()));
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder  = new StringBuilder();
		String itemIds = this.getItems().stream().map(InventoryItem::getItemId).toList().stream()
		                     .map(Object::toString)
		                     .collect(Collectors.joining(", "));
		 builder.append(String.format("""
                       Supplier ID: %d
                       Supplier name: %s
                       Available item IDs: %s
                         """, this.getSupplierId(), this.getSupplierName(), itemIds));
		 return builder.toString();
	}
}
