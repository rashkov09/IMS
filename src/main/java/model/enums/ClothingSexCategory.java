package model.enums;

public enum ClothingSexCategory {
	MEN("Men's Clothing"),
	WOMEN("Women's Clothing"),
	CHILDREN("Children's Clothing");

	private final String category;

	ClothingSexCategory(String category) {
		this.category = category;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		ClothingSexCategory[] values = ClothingSexCategory.values();
		for (int i = 0; i < values.length; i++) {
			builder.append(i + 1).append(". ").append(values[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}

	public String getCategoryName() {
		return category;
	}

	@Override
	public String toString() {
		return category;
	}
}