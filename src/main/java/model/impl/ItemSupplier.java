package model.impl;

import model.enums.payment.PaymentMethod;

import java.time.LocalDate;

public class ItemSupplier {
	private static Long ID_COUNTER=0L;
	private Long supplierId;
	private String supplierName;
	private String supplierEmail;
	private String supplierPhone;
	private String supplierAddress;
	private LocalDate contractStart;
	private LocalDate contractEnd;
	private PaymentMethod paymentMethod;

	public ItemSupplier(
		String supplierName, String supplierEmail, String supplierPhone, String supplierAddress, LocalDate contractStart,
		LocalDate contractEnd, PaymentMethod paymentMethod) {
		setSupplierId();
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhone = supplierPhone;
		this.supplierAddress = supplierAddress;
		this.contractStart = contractStart;
		this.contractEnd = contractEnd;
		this.paymentMethod = paymentMethod;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	private void setSupplierId() {
		this.supplierId = ++ID_COUNTER;
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
}
