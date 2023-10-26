package model.enums;

public enum ItemCategory {
	ELECTRONICS("Electronics"),
	FOOD_GROCERIES("Food and Groceries"),
	CLOTHING_APPAREL("Clothing and Apparel"),
	FURNITURE("Furniture");

	/*  <- For future development ->
	AUTOMOTIVE_PARTS("Automotive Parts"),
	BOOKS_MEDIA("Books and Media"),
	TOYS_GAMES("Toys and Games"),
	HEALTH_BEAUTY("Health and Beauty"),
	HOME_GARDEN("Home and Garden"),
	SPORTS_FITNESS("Sports and Fitness"),
	JEWELRY_WATCHES("Jewelry and Watches"),
	OFFICE_SUPPLIES("Office Supplies"),
	PET_SUPPLIES("Pet Supplies"),
	ELECTRICAL_COMPONENTS("Electrical Components"),
	MEDICAL_SUPPLIES("Medical Supplies");
	*/

	private final String categoryName;

	ItemCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ItemCategory.values().length; i++) {
			builder.append(i + 1).append(". ").append(ItemCategory.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}
	}
