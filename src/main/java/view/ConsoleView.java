package view;

import model.impl.user.User;

public interface ConsoleView {
	void showMenu(ConsoleView invoker, User user);
}
