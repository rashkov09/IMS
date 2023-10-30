package service.impl;

import data.UserData;
import model.enums.order.OrderStatus;
import model.enums.user.UserRole;
import model.impl.order.InventoryOrder;
import model.impl.order.OrderItemLine;
import model.impl.user.CustomerUser;
import model.impl.user.EmployeeUser;
import model.impl.user.User;
import service.UserService;
import util.ConsoleReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static constant.Shared.HORIZONTAL_LINE_BREAK;

public class UserServiceImpl implements UserService {

	private static final UserData userData = new UserData();

	@Override
	public User login() {
		System.out.println("Username:");
		String username = ConsoleReader.readString();
		System.out.println("Password:");
		String password = ConsoleReader.readString();
		try {
			User user = userData.getByUsernameAndPassword(username, password);
			if (user == null) {
				throw new RuntimeException("Wrong username or password!");
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public User registerUser() {
		User user = createUser(UserRole.CUSTOMER);
		if (userData.add(user)) {
			return user;
		}
		return null;
	}

	@Override
	public String addEmployeeUser() {
		User user = createUser(UserRole.EMPLOYEE);
		if (userData.add(user)) {
			return "User added successfully";
		}
		return "User addition failed!";
	}

	private User createUser(UserRole role) {
		System.out.println("Name:");
		String name = ConsoleReader.readString();
		System.out.println("phone:");
		String phone = ConsoleReader.readString();
		System.out.println("Email:");
		String email = ConsoleReader.readString();
		System.out.println("username:");
		String username = ConsoleReader.readString();
		System.out.println("Password:");
		String password = ConsoleReader.readString();
		Long id = userData.getLastId() + 1;
		User user = null;
		switch (role) {
			case EMPLOYEE -> user = new EmployeeUser(name, phone, email, username, password, id, UserRole.EMPLOYEE);
			case CUSTOMER -> user = new CustomerUser(name, phone, email, username, password, id, UserRole.CUSTOMER);
		}
		return user;
	}

	@Override
	public String removeEmployeeUser() {
		System.out.println("Please, insert user ID:");
		Long userId = ConsoleReader.readILong();
		return userData.removeById(userId) ? String.format("User with ID %d removed successfully", userId)
		                                   : "User not found!";
	}

	@Override
	public String displayAllUsers() {
		StringBuilder builder = new StringBuilder();
		userData.getAll().forEach(
			user -> builder.append(HORIZONTAL_LINE_BREAK).append(user.toString()).append(System.lineSeparator()));
		return builder.toString();
	}

	@Override
	public String modifyAdminStatus() {
		System.out.println("Please, insert user ID:");
		long id = ConsoleReader.readILong();
		List<User> users = userData.getAll();
		String result = "Nothing was modified!";
		try {
			EmployeeUser selectedUser =
				users.stream().filter(user -> user.getUserRole().equals(UserRole.EMPLOYEE) && user.getId().equals(id))
				     .map(u -> (EmployeeUser) u).findFirst().orElseThrow();
			System.out.printf("Current user status: isAdmin = %s\n", selectedUser.getAdmin());
			switch (selectedUser.getAdmin().toString()) {
				case "true" -> {
					System.out.println("Do you want to deactivate ADMIN status? (y/n)");
					String answer = ConsoleReader.readString();
					if (answer.equals("y")) {
						users.stream().filter(user -> user.getUserRole().equals(UserRole.EMPLOYEE) && user.getId().equals(id))
						     .map(u -> (EmployeeUser) u).findFirst().ifPresent(user -> user.setAdmin(false));
						userData.update(users);
						result = String.format("ADMIN status deactivated for userId: %d", id);
					}
				}
				case "false" -> {
					System.out.println("Do you want to activate ADMIN status? (y/n)");
					String answer = ConsoleReader.readString();
					if (answer.equals("y")) {
						users.stream().filter(user -> user.getUserRole().equals(UserRole.EMPLOYEE) && user.getId().equals(id))
						     .map(u -> (EmployeeUser) u).findFirst().ifPresent(user -> user.setAdmin(true));
						userData.update(users);
						result = String.format("ADMIN status activated for userId: %d", id);
					}
				}
			}
		} catch (NoSuchElementException e) {
			result = String.format("Employee user with id %d not found!", id);
		}
		return result;
	}

	@Override
	public void displayUserProfile(User user) {
		System.out.println(user.getProfileInfo());
	}

	@Override
	public String addItemToCart(User user, OrderItemLine item) {
		List<User> updatedUsers = userData.getAll().stream()
		                                  .map(currentUser -> {
			                                  if (currentUser.getId().equals(user.getId())) {
				                                  CustomerUser updatedCustomer = (CustomerUser) currentUser;
				                                  updatedCustomer.getUserCart().add(item);
				                                  return updatedCustomer;
			                                  }
			                                  return currentUser;
		                                  })
		                                  .toList();
		userData.update(updatedUsers);
		return "Item added to cart!";
	}

	@Override
	public User getUpdatedUser(User customer) {
		return userData.getById(customer.getId());
	}

	@Override
	public String addOrderToHistory(User user, InventoryOrder inventoryOrder) {
		List<User> updatedUsers = userData.getAll().stream()
		                                  .map(currentUser -> {
			                                  if (currentUser.getId().equals(user.getId())) {
				                                  CustomerUser updatedCustomer = (CustomerUser) currentUser;
				                                  updatedCustomer.getOrderHistory().add(inventoryOrder);
				                                  updatedCustomer.setUserCart(new ArrayList<>());
				                                  return updatedCustomer;
			                                  }
			                                  return currentUser;
		                                  })
		                                  .toList();
		userData.update(updatedUsers);
		return "Order added successfully!";
	}

	@Override
	public void updateOrderHistory(CustomerUser customer, long orderId) {
		List<User> updatedUsers = userData.getAll().stream().map(user -> {
			if (user.getId().equals(customer.getId())) {
				customer.setOrderHistory(customer.getOrderHistory().stream().peek(order -> {
					if (order.getOrderId().equals(orderId)) {
						order.setOrderStatus(OrderStatus.CANCELED);
						order.setStampModified(LocalDateTime.now());
					}
				}).collect(Collectors.toList()));
				return customer;
			}
			return user;
		}).toList();
		userData.update(updatedUsers);
	}
}
