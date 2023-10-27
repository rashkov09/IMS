package service.impl;

import data.UserData;
import model.enums.user.UserRole;
import model.impl.user.EmployeeUser;
import model.impl.user.User;
import service.UserService;
import util.ConsoleReader;

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
				throw new RuntimeException("User not found");
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public User registerUser() {
		return null;
	}

	@Override
	public String addEmployeeUser() {
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
		User user = new EmployeeUser(name, phone, email, username, password, id, UserRole.EMPLOYEE);
		if (userData.add(user)) {
			return "User added successfully";
		}
		return "User addition failed!";
	}

	@Override
	public String removeEmployeeUser() {
		return null;
	}
}
