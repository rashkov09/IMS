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

	@Override
	public String toString() {
		return String.format("""
                       User ID: %d
                       Username: %s
                       User role: %s
                       """,this.getId(),this.getUsername(),this.getUserRole().name());
	}

	public String getProfileInfo() {
		return String.format("""
                       User ID: %d
                       Name: %s
                       Phone: %s
                       Email: %s
                       Username: %s
		                     """,this.getId(),this.getName(),this.getPhone(),this.getEmail(),this.getUsername());
	}
}
