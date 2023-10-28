package data;

import com.google.gson.reflect.TypeToken;
import model.impl.item.InventoryItem;
import model.impl.user.User;

import java.util.List;

import static constant.Shared.USER_FILE_PATH;

public class UserData extends PersistenceUnit<User> implements Data<User> {

	private static final TypeToken<List<User>> typeToken = new TypeToken<>() {
	};

	public UserData() {
		super(USER_FILE_PATH);
	}

	@Override
	public User getById(Long id) {
		return this.fetchAll(new TypeToken<>() {
		}).stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public boolean add(User user) {
		List<User> users = this.fetchAll(typeToken);
		users.add(user);
		return this.save(users);
	}

	@Override
	public boolean removeById(Long id) {
		List<User> users = this.fetchAll(typeToken);
		if (users.removeIf(user -> user.getId().equals(id))) {
			this.save(users);
			return true;
		}
		return false;
	}

	@Override
	public Long getLastId() {
		return this.fetchAll(typeToken).stream().map(User::getId).max(Long::compareTo).orElse(0L);
	}

	@Override
	public List<User> getAll(){
		return this.fetchAll(typeToken);
	}

	public User getByUsernameAndPassword(String username, String password) {
		return this.fetchAll(typeToken).stream()
		           .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst()
		           .orElse(null);
	}

}
