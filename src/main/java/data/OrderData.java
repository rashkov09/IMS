package data;

import com.google.gson.reflect.TypeToken;
import model.impl.order.InventoryOrder;

import java.util.List;

import static constant.Shared.ORDER_FILE_PATH;

public class OrderData extends PersistenceUnit<InventoryOrder> implements Data<InventoryOrder> {
	private static final TypeToken<List<InventoryOrder>> typeToken = new TypeToken<>() {
	};
	public OrderData() {
		super(ORDER_FILE_PATH);
	}

	@Override
	public InventoryOrder getById(Long id) {
		return this.fetchAll(typeToken).stream().filter(order -> order.getOrderId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public boolean add(InventoryOrder order) {
		List<InventoryOrder> inventoryOrders = this.fetchAll(typeToken);
		inventoryOrders.add(order);
		return this.save(inventoryOrders);
	}

	@Override
	public boolean removeById(Long id) {
		return false;
	}

	@Override
	public Long getLastId() {
		return this.fetchAll(typeToken).stream().map(InventoryOrder::getOrderId).max(Long::compareTo).orElse(0L);
	}

	@Override
	public List<InventoryOrder> getAll() {
		return this.fetchAll(typeToken);
	}
}
