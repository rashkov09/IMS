package model.enums;

public enum ClothingType {
	SHIRT("Shirt"),
	PANTS("Pants"),
	SHOES("Shoes"),
	JACKET("Jacket"),
	DRESS("Dress"),
	HAT("Hat"),
	SOCKS("Socks"),
	UNDERWEAR("Underwear"),
	ACCESSORY("Accessory"),
	OTHER("Other");

	private final String type;

	ClothingType(String type) {
		this.type = type;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ClothingType.values().length; i++) {
			builder.append(i + 1).append(". ").append(ClothingType.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}

	public String getTypeName() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}
}

