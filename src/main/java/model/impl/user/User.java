package model.impl.user;

import model.enums.user.UserRole;

public class User extends AbstractUser{
	private Long id;

	private UserRole userRole;

	public User(String name, String phone, String email, String username, String password, Long id, UserRole userRole) {
		super(name, phone, email, username, password);
		this.id = id;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
