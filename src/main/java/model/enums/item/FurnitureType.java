package model.enums.item;

public enum FurnitureType {
	SOFAS("Sofas"),
	TABLES("Tables"),
	CHAIRS("Chairs"),
	BEDS("Beds"),
	CABINETS("Cabinets"),
	DESKS("Desks"),
	SHELVES("Shelves"),
	WARDROBES("Wardrobes"),
	DINING_SETS("Dining Sets"),
	COFFEE_TABLES("Coffee Tables"),
	OTHER("Other Furniture");

	private final String type;

	FurnitureType(String type) {
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
		for (int i = 0; i < FurnitureType.values().length; i++) {
			builder.append(i + 1).append(". ").append(FurnitureType.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}
}
