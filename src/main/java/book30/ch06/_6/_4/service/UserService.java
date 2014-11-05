package book30.ch06._6._4.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import book30.ch06.domain.User;

public interface UserService {
	void add(User user);
	void deleteAll();
	void update(User user);	
	void upgradeLevels();
	
	User get(String id);

	List<User> getAll();
}
