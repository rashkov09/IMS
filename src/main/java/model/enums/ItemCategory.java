package model.enums;

public enum ItemCategory {
	NOT_CATEGORIZED("Item does not have a category"),
	ELECTRONICS("Electronics"),
	CLOTHING_APPAREL("Clothing and Apparel"),
	FOOD_GROCERIES("Food and Groceries"),
	FURNITURE("Furniture"),
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

	private  String categoryName;

	ItemCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}
}
