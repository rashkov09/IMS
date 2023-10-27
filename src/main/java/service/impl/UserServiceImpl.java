package service.impl;

import data.UserData;
import model.impl.user.User;
import service.UserService;
import util.ConsoleReader;

public class UserServiceImpl  implements UserService {
	private static final UserData userData = new UserData();
	@Override
	public User login() {
		System.out.println("Username:");
		String username = ConsoleReader.readString();
		System.out.println("Password:");
		String password = ConsoleReader.readString();
		try {
			User user =  userData.getByUsernameAndPassword(username, password);
			if (user == null){
				throw new RuntimeException("User not found");
			}
			return  user;
		} catch (Exception e){
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
		return null;
	}

	@Override
	public String removeEmployeeUser() {
		return null;
	}
}
