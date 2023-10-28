package data;

import com.google.gson.reflect.TypeToken;
import model.impl.item.InventoryItem;
import model.impl.supplier.ItemSupplier;

import java.util.List;
import java.util.stream.Collectors;

import static constant.Shared.SUPPLIER_FILE_PATH;

public class SupplierData extends PersistenceUnit<ItemSupplier> implements Data<ItemSupplier> {

	private static final TypeToken<List<ItemSupplier>> typeToken = new TypeToken<>() {
	};

	public SupplierData() {
		super(SUPPLIER_FILE_PATH);
	}

	@Override
	public ItemSupplier getById(Long id) {
		return this.fetchAll(typeToken).stream().filter(supplier -> supplier.getSupplierId().equals(id)).findFirst()
		           .orElse(null);
	}

	@Override
	public boolean add(ItemSupplier supplier) {
		List<ItemSupplier> suppliers = this.fetchAll(typeToken);
		suppliers.add(supplier);
		return this.save(suppliers);
	}

	@Override
	public boolean removeById(Long id) {
		List<ItemSupplier> suppliers = this.fetchAll(typeToken);
		suppliers.removeIf(supplier -> supplier.getSupplierId().equals(id));
		return this.save(suppliers);
	}

	@Override
	public Long getLastId() {
		return this.fetchAll(typeToken).stream().map(ItemSupplier::getSupplierId).max(Long::compareTo).orElse(0L);
	}

	@Override
	public List<ItemSupplier> getAll() {
		return this.fetchAll(typeToken);
	}

	public boolean addItemToSupplier(long supplierId, InventoryItem item) {
		if (this.getById(supplierId) == null) {
			throw new RuntimeException("Supplier not found!");
		}
		List<ItemSupplier> updateSuppliers = this.fetchAll(typeToken).stream().map(supplier -> {
			if (supplier.getSupplierId().equals(supplierId)) {
				return supplier.addItem(item);
			} else {
				return supplier;
			}
		}).collect(
			Collectors.toList());
		return this.save(updateSuppliers);
	}
}
