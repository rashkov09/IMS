package data;

import com.google.gson.reflect.TypeToken;
import model.impl.user.User;

import java.util.List;

import static constant.Shared.USER_FILE_PATH;

public class UserData implements Data<User> {

	private static final DataPersistence<User> userPersistenceUnit = new PersistenceUnit<>(USER_FILE_PATH);
	private static final TypeToken<List<User>> typeToken = new TypeToken<>() {
	};

	@Override
	public User getById(Long id) {
		return userPersistenceUnit.fetchAll(new TypeToken<>() {
		}).stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public boolean add(User user) {
		List<User> users = userPersistenceUnit.fetchAll(typeToken);
		users.add(user);
		return userPersistenceUnit.save(users);
	}

	@Override
	public boolean removeById(Long id) {
		List<User> users = userPersistenceUnit.fetchAll(typeToken);
		if (users.removeIf(user -> user.getId().equals(id))) {
			userPersistenceUnit.save(users);
			return true;
		}
		return false;
	}

	@Override
	public Long getLastId() {
		return userPersistenceUnit.fetchAll(typeToken).stream().map(User::getId).max(Long::compareTo).orElse(0L);
	}

	@Override
	public List<User> getAll() {
		return userPersistenceUnit.fetchAll(typeToken);
	}

	public User getByUsernameAndPassword(String username, String password) {
		return userPersistenceUnit.fetchAll(typeToken).stream()
		                          .filter(
			                          user -> user.getUsername().equals(username) && user.getPassword().equals(password))
		                          .findFirst()
		                          .orElse(null);
	}

	@Override
	public void update(List<User> users) {
		userPersistenceUnit.save(users);
	}
}
