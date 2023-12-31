package service.impl;

import data.OrderData;
import model.enums.order.OrderStatus;
import model.enums.payment.PaymentMethod;
import model.impl.item.InventoryItem;
import model.impl.order.BuyOrder;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.order.SellOrder;
import model.impl.supplier.ItemSupplier;
import model.impl.user.CustomerUser;
import model.impl.user.User;
import service.ItemSupplierService;
import service.OrderService;
import util.ConsoleReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class OrderServiceImpl implements OrderService {

	private static final OrderData orderData = new OrderData();
	private static final ItemSupplierService supplierService = new ItemSupplierServiceImpl();

	@Override
	public InventoryOrder addOrder(User user) {
		Long orderId = orderData.getLastId() + 1;
		switch (user.getUserRole()) {
			case EMPLOYEE -> {
				ItemSupplier itemSupplier = getItemSupplier();
				BuyOrder inventoryOrder = new BuyOrder(orderId, user.getId(), itemSupplier);
				String choice = "y";
				while (!choice.equalsIgnoreCase("n")) {
					addItemToOrder(itemSupplier, inventoryOrder);
					System.out.println("Do you want to add another item to order? (y/n)");
					choice = ConsoleReader.readString();
				}
				System.out.println(inventoryOrder.printOrder());
				if (orderData.add(inventoryOrder)) {
					System.out.println("Order added successfully!");
					return inventoryOrder;
				}
			}
			case CUSTOMER -> {
				CustomerUser customer = (CustomerUser) user;
				if (customer.getUserCart().isEmpty()) {
					System.out.println("No items in cart!");
					return null;
				}
				InventoryOrder inventoryOrder = new SellOrder(orderId, customer.getId());
				inventoryOrder.setOrderItems(customer.getUserCart());
				customer.setUserCart(new ArrayList<>());
				System.out.println("Please, choose payment method:");
				System.out.println(PaymentMethod.getAll());
				int paymentChoice = ConsoleReader.readInt() - 1;
				inventoryOrder.setPaymentMethod(PaymentMethod.values()[paymentChoice]);
				System.out.println(
					"You are going to create an order with the items from your cart. Do yuo want to proceed? (y/n)");
				String answer = ConsoleReader.readString();
				if (answer.equals("n")) {
					return null;
				} else if (answer.equals("y")) {
					if (orderData.add(inventoryOrder)) {
						System.out.println("Order added successfully!");
						return inventoryOrder;
					}
				} else {
					System.out.println("Invalid answer!");
					return null;
				}
			}
		}
		System.out.println("Order addition failed!");
		return null;
	}

	private void addItemToOrder(ItemSupplier itemSupplier, BuyOrder inventoryOrder) {
		System.out.println("Please, insert item ID:");
		long itemId = ConsoleReader.readILong();
		InventoryItem inventoryItem =
			itemSupplier.getItems().stream().filter(item -> item.getItemId().equals(itemId)).findFirst().orElse(null);
		if (inventoryItem == null) {
			System.out.printf("This supplier does not support item with id %d!\n", itemId);
		} else {
			System.out.println("Please, insert item quantity:");
			int quantity = ConsoleReader.readInt();
			inventoryItem.setItemQuantity(inventoryItem.getItemQuantity() + quantity);
			inventoryOrder.getOrderItems().add(new OrderItemLine(inventoryItem, quantity));
		}
	}

	private ItemSupplier getItemSupplier() {
		System.out.println("Please, choose supplier id from the list:");
		System.out.println(supplierService.displaySuppliersOrderDetails());
		long supplierId = ConsoleReader.readILong();
		return supplierService.getSupplierById(supplierId);
	}

	@Override
	public String removeOrderById() {
		return null;
	}

	@Override
	public String displayAllOrders() {
		StringBuilder builder = new StringBuilder();
		orderData.getAll().forEach(
			order -> builder.append(HORIZONTAL_LINE_BREAK).append(order.printOrder()).append(System.lineSeparator()));
		return builder.toString();
	}

	@Override
	public boolean cancelOrder(long orderId) {
		List<InventoryOrder> updatedOrders = orderData.getAll().stream().map(order -> {
			if (order.getOrderId().equals(orderId)) {
				order.setOrderStatus(OrderStatus.CANCELED);
				order.setStampModified(LocalDateTime.now());
				return order;
			}
			return order;
		}).toList();
		orderData.update(updatedOrders);
		return orderData.getAll().stream().filter(order -> order.getOrderId().equals(orderId))
		                .map(order -> order.getOrderStatus().equals(OrderStatus.CANCELED)).findFirst()
		                .isPresent();
	}
}