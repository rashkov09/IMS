import model.iface.Item;
import model.impl.ElectronicsItem;
import model.impl.InventoryItem;

import java.time.LocalDate;

public class InventoryManagementSystemApp {

	public static void main(String[] args) {
		InventoryItem item = new ElectronicsItem("LG400", "LG", "South Korea", "40 inch TV", 4, LocalDate.now(), 3);

		System.out.println(item.getItemDetails());
		System.out.println(item.getItemId());
		System.out.println(item.getItemCategory());
		System.out.println(item.getItemPrice());
		System.out.println(item.isBreakable());
		System.out.println(item.displayItemDescription());
		item.handleBrokenItem();

		System.out.println(item.displayItemDescription());


	}

}
