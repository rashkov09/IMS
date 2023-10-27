import view.EmployeeView;

public class InventoryManagementSystemApp {

	private static final EmployeeView EMPLOYEE_VIEW = new EmployeeView();

	public static void main(String[] args) {
		EMPLOYEE_VIEW.showMenu(EMPLOYEE_VIEW);
	}
}
