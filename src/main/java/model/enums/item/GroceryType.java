package model.enums.item;

public enum GroceryType {
	DAIRY("Dairy Products"),
	FRESH_PRODUCE("Fresh Produce"),
	CANNED_GOODS("Canned Goods"),
	FROZEN_FOODS("Frozen Foods"),
	BEVERAGES("Beverages"),
	BAKERY("Bakery Items"),
	SNACKS("Snacks"),
	CONDIMENTS("Condiments"),
	SPICES("Spices"),
	NON_ALCOHOLIC("Non-Alcoholic Beverages"),
	ALCOHOLIC("Alcoholic Beverages");

	private final String type;

	GroceryType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < GroceryType.values().length; i++) {
			builder.append(i+1).append(". ").append(GroceryType.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}
}
