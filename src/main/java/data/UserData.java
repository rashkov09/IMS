package data;

import com.google.gson.reflect.TypeToken;
import model.impl.user.User;

import java.util.List;

import static constant.Shared.USER_FILE_PATH;

public class UserData extends PersistenceUnit<User> implements Data<User> {

	public UserData() {
		super(USER_FILE_PATH);
	}

	@Override
	public User getById(Long id) {
		return this.fetchAll(new TypeToken<>(){}).stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public boolean add(User user) {
		List<User> users = this.fetchAll(new TypeToken<>(){});
		users.add(user);
		return this.save(users);
	}

	public User getByUsernameAndPassword(String username, String password) {
		return this.fetchAll(new TypeToken<>(){}).stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
	}
}
