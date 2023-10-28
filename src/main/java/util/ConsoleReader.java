package util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public final class ConsoleReader {

	private static final String INVALID_INT = "%s is not a number. Try again: ";
	private static final Scanner scanner = new Scanner(System.in);

	private ConsoleReader() {
		throw new UnsupportedOperationException();
	}

	public static String readString() {
		String line = scanner.nextLine();
		while (line == null || line.isEmpty()){
			line = scanner.nextLine();
			System.out.println("Invalid input! Cannot be empty!");
		}
		return line;
	}

	public static int readInt() {
		while (!scanner.hasNextInt()) {
			String input = scanner.next();
			System.out.printf(INVALID_INT, input);
		}
		int input = scanner.nextInt();
		scanner.nextLine();
		return input;
	}
	public static Long readILong() {
		while (!scanner.hasNextLong()) {
			String input = scanner.next();
			System.out.printf(INVALID_INT, input);
		}
		long input = scanner.nextLong();
		scanner.nextLine();
		return input;
	}
	public static BigDecimal readBigDecimal() {
		while (!scanner.hasNextBigDecimal()) {
			String input = scanner.next();
			System.out.printf(INVALID_INT, input);
		}
		BigDecimal input = scanner.nextBigDecimal();
		scanner.nextLine();
		return input;
	}

	public static LocalDate readDateString() {
		String datePattern = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
		String date = scanner.nextLine();
		while (!date.matches(datePattern) || date.isEmpty()){
			System.out.println("Invalid date input! Format should be yyyy-MM-dd");
			date = scanner.nextLine();
		}
		String[] data = date.split("-");
		return LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
	}
}