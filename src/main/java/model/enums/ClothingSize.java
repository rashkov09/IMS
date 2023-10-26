package model.enums;

public enum ClothingSize {
	XS("Extra Small"),
	S("Small"),
	M("Medium"),
	L("Large"),
	XL("Extra Large"),
	XXL("Double Extra Large"),
	XXXL("Triple Extra Large");

	private String sizeName;

	ClothingSize(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getSizeName() {
		return sizeName;
	}

	@Override
	public String toString() {
		return sizeName;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ClothingSize.values().length; i++) {
			builder.append(i + 1).append(". ").append(ClothingSize.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}
}
