package service;

import model.impl.user.User;

public interface UserService {

	User login();

	User registerUser();

	String addEmployeeUser();

	String removeEmployeeUser();
}
