package book30.ch05._2._1.service;

import java.util.List;

import book30.ch05._2._1.dao.UserDao;
import book30.ch05._2._1.domain.Level;
import book30.ch05._2._1.domain.User;

public class UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for(User user: users) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			case BASIC: 
				return user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER;
			case SILVER: 
				return user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD;
			case GOLD: 
				return false;
			default: 
				throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}

	public void add(User user) {
		if (user.getLevel() == null)
			user.setLevel(Level.BASIC);
		userDao.add(user);
	}
}
