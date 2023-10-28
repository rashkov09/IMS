package model.impl.order;

import model.enums.order.OrderType;
import model.enums.order.PriceModifier;
import model.iface.Processable;
import model.impl.ItemSupplier;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.math.BigDecimal;

public class BuyOrder extends InventoryOrder implements Processable {
	private ItemSupplier supplier;
	private User employee;

	public BuyOrder(Long orderId,EmployeeUser employee, ItemSupplier supplier) {
		super(orderId);
		this.setOrderType(OrderType.BUY);
		this.employee=employee;
		setSupplier(supplier);
	}

	public ItemSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(ItemSupplier supplier) {
		this.supplier = supplier;
		super.setPaymentMethod(supplier.getPaymentMethod());
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	@Override
	public BigDecimal getTotalOrderAmount() {
		return this.getOrderItems().entrySet()
		                      .stream()
		                      .map(entry -> entry.getKey().getItemPrice().multiply(PriceModifier.valueOf(entry.getKey().getItemCategory().name()).getPriceModifier()).multiply(BigDecimal.valueOf(entry.getValue())))
		                      .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String printOrder() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Order created: ").append(this.getStampCreated())
			.append("\t\t")
			.append("Order modified: ").append(this.getStampModified() != null ? this.getStampModified() : "never")
			.append(System.lineSeparator())
			.append("Order status: ").append(this.getOrderStatus().name()).append(System.lineSeparator());
		this.getOrderItems().forEach((key, value) -> {
			builder.append("Item: ").append(key.displayItemDescription()).append("\t\t").append("Qty: ").append(value).append("\t\t")
			       .append("TOTAL: ").append(key.getItemPrice().multiply(PriceModifier.valueOf(key.getItemCategory().name()).getPriceModifier()).multiply(BigDecimal.valueOf(value)))
			       .append(System.lineSeparator());
		});
		return builder.toString();
	}

	@Override
	public void process() {
		// TODO process buy order
	}
}
