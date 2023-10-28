package model.enums.payment;

import model.enums.item.GroceryType;

public enum PaymentMethod {
	CARD("Card payment"),
	BANK_TRANSFER("Direct bank transfer"),
	CASH("Cash payment");

	private final String paymentMethod;

	PaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethodName() {
		return paymentMethod;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < PaymentMethod.values().length; i++) {
			builder.append(i + 1).append(". ").append(PaymentMethod.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}
}
