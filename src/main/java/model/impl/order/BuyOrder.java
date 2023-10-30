package model.impl.order;

import model.enums.order.OrderStatus;
import model.enums.order.OrderType;
import model.enums.order.PriceModifier;
import model.enums.payment.PaymentMethod;
import model.iface.Processable;
import model.impl.supplier.ItemSupplier;
import model.impl.user.EmployeeUser;
import model.impl.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BuyOrder extends InventoryOrder implements Processable {

	private ItemSupplier supplier;
	private final User employee;

	public BuyOrder(Long orderId, EmployeeUser employee, ItemSupplier supplier) {
		super(orderId);
		this.setOrderType(OrderType.BUY);
		this.employee = employee;
		setSupplier(supplier);
	}

	public BuyOrder(
		List<OrderItemLine> orderItems, OrderStatus orderStatus,
		LocalDateTime stampCreated, LocalDateTime stampModified, Long orderId, OrderType orderType,
		PaymentMethod paymentMethod, ItemSupplier supplier, User employee) {
		super(orderItems, orderStatus, stampCreated, stampModified, orderId, orderType, paymentMethod);
		this.supplier = supplier;
		this.employee = employee;
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

	@Override
	public BigDecimal getTotalOrderAmount() {
		return this.getOrderItems()
		           .stream()
		           .map(line -> line.getItem().getItemPrice().multiply(
			                            PriceModifier.valueOf(line.getItem().getItemCategory().name()).getPriceModifier())
		                            .multiply(BigDecimal.valueOf(
			                            line.getQuantity())))
		           .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Order ID :").append(this.getOrderId()).append(System.lineSeparator())
			.append("Supplier: ").append(this.getSupplier().getSupplierName()).append(System.lineSeparator())
			.append("Employee: ").append(this.getEmployee().getUsername()).append(System.lineSeparator())
			.append("Order created: ").append(this.getStampCreated())
			.append("\t\t")
			.append("Order modified: ").append(this.getStampModified() != null ? this.getStampModified() : "never")
			.append(System.lineSeparator())
			.append("Order status: ").append(this.getOrderStatus().name()).append(System.lineSeparator());
		this.getOrderItems().forEach(itemLine -> builder.append("Item: ").append(itemLine.getItem().displayItemDescription()).append("\t\t").append("Qty: ").append(itemLine.getQuantity())
	                                                .append("\t\t")
	                                                .append("PRICE: ").append( itemLine.getItem().getItemPrice().multiply(PriceModifier.valueOf(itemLine.getItem().getItemCategory().name()).getPriceModifier()))
	                                                .append("\t\t")
	                                                .append("TOTAL: ").append(
			       itemLine.getItem().getItemPrice().multiply(PriceModifier.valueOf(itemLine.getItem().getItemCategory().name()).getPriceModifier())
			          .multiply(BigDecimal.valueOf(itemLine.getQuantity())))
	                                                .append(System.lineSeparator()));
		builder.append("ORDER TOTAL: ").append(this.getTotalOrderAmount());
		return builder.toString();
	}

	@Override
	public void process() {
		// TODO process buy order
	}
}
