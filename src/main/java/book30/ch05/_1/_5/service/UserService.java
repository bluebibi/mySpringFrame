package book30.ch05._1._5.service;

import java.util.List;

import book30.ch05._1._5.dao.UserDao;
import book30.ch05._1._5.domain.Level;
import book30.ch05._1._5.domain.User;


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

	private void upgradeLevel(User user) {
		/* List 5-25
		if( user.getLevel() == Level.BASIC ) user.setLevel(Level.SILVER);
		else if( user.getLevel() == Level.SILVER) user.setLevel(Level.GOLD);
		userDao.update(user);
		 */
		user.upgradeLevel();
		userDao.update(user);
	}

	public void add(User user) {
		if (user.getLevel() == null)
			user.setLevel(Level.BASIC);
		userDao.add(user);
	}
}
