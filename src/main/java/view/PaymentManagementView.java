package view;

import model.impl.user.User;
import service.PaymentService;
import service.impl.PaymentServiceImpl;
import util.ConsoleRangeReader;

public class PaymentManagementView implements ConsoleView{
	private final static PaymentService paymentService = new PaymentServiceImpl();
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String MENU_STRING = """
	                                         <-- You are in Payment management menu -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Process payment
	                                         2. Display all payments
	                                         3. Search
	                                                                                  
	                                         0. Back
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker, User user) {
		System.out.println(MENU_STRING);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(paymentService.processPayment());
				this.showMenu(invoker, user);
			}
			case 2 -> {
				System.out.println(paymentService.displayAllPayments());
				this.showMenu(invoker,user);
			}
			case 3 -> {
			  // TODO search payments
				this.showMenu(invoker,user);
			}
			case 0 -> invoker.showMenu(this, user);
		}
	}
}
