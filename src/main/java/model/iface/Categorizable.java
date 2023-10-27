package model.iface;

import model.enums.item.ItemCategory;

public interface Categorizable {

	void setItemCategory(ItemCategory itemCategory);
	ItemCategory getItemCategory();

}
