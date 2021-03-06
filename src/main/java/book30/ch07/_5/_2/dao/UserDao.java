package book30.ch07._5._2.dao;

import java.util.List;
import book30.ch07.domain.User;

public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	void update(User user);
}