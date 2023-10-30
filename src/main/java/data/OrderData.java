package data;

import com.google.gson.reflect.TypeToken;
import model.impl.order.InventoryOrder;

import java.util.List;

import static constant.Shared.ORDER_FILE_PATH;

public class OrderData implements Data<InventoryOrder> {

	private static final DataPersistence<InventoryOrder> orderPersistenceUnit = new PersistenceUnit<>(ORDER_FILE_PATH);
	private static final TypeToken<List<InventoryOrder>> typeToken = new TypeToken<>() {
	};

	@Override
	public InventoryOrder getById(Long id) {
		return orderPersistenceUnit.fetchAll(typeToken).stream().filter(order -> order.getOrderId().equals(id)).findFirst()
		                           .orElse(null);
	}

	@Override
	public boolean add(InventoryOrder order) {
		List<InventoryOrder> inventoryOrders = orderPersistenceUnit.fetchAll(typeToken);
		inventoryOrders.add(order);
		return orderPersistenceUnit.save(inventoryOrders);
	}

	@Override
	public boolean removeById(Long id) {
		return false;
	}

	@Override
	public Long getLastId() {
		return orderPersistenceUnit.fetchAll(typeToken).stream().map(InventoryOrder::getOrderId).max(Long::compareTo)
		                           .orElse(0L);
	}

	@Override
	public List<InventoryOrder> getAll() {
		return orderPersistenceUnit.fetchAll(typeToken);
	}

	@Override
	public void update(List<InventoryOrder> list) {
		orderPersistenceUnit.save(list);
	}
}
