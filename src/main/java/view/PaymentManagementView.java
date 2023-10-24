package view;

import service.PaymentService;
import service.impl.PaymentServiceImpl;
import util.ConsoleRangeReader;

public class PaymentManagementView implements ConsoleView{
	private final static PaymentService paymentService = new PaymentServiceImpl();
	private static final int MAX_CHOICE = 4;
	private static final int MIN_CHOICE = 0;
	private static final String menuString = """
	                                         <-- You are in Payment management menu -->
	                                                                                  
	                                         Please, select an option to continue:
	                                                                                  
	                                         1. Process payment
	                                         2. Display all payments
	                                         3. Search
	                                                                                  
	                                         0. Back
	                                          """;

	@Override
	public void showMenu(ConsoleView invoker) {
		System.out.println(menuString);
		int choice = ConsoleRangeReader.readInt(MIN_CHOICE, MAX_CHOICE);
		switch (choice) {
			case 1 -> {
				System.out.println(paymentService.processPayment());
				this.showMenu(invoker);
			}
			case 2 -> {
				System.out.println(paymentService.displayAllPayments());
				this.showMenu(invoker);
			}
			case 3 -> {
			  // TODO search payments
				this.showMenu(invoker);
			}
			case 0 -> invoker.showMenu(this);
		}
	}
}
