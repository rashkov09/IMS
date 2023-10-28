package model.impl.user;

import model.enums.user.UserRole;

public class EmployeeUser extends User {
	private Boolean isAdmin;

	public EmployeeUser(
		String name, String phone, String email, String username, String password, Long id, UserRole userRole) {
		super(name, phone, email, username, password, id, userRole);
		this.isAdmin = false;
	}
	public EmployeeUser(
		String name, String phone, String email, String username, String password, Long id, UserRole userRole, Boolean isAdmin) {
		super(name, phone, email, username, password, id, userRole);
		this.isAdmin = isAdmin;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	@Override
	public String toString() {
		return super.toString()+String.format("Is ADMIN: %s\n",this.getAdmin());
	}
}
