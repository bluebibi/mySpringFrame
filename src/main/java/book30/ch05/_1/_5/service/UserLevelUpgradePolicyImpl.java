package book30.ch05._1._5.service;

import org.springframework.beans.factory.annotation.Autowired;

import book30.ch05._1._5.dao.UserDao;
import book30.ch05._1._5.domain.Level;
import book30.ch05._1._5.domain.User;

public class UserLevelUpgradePolicyImpl implements UserLevelUpgradePolicy {
	@Autowired
	UserDao userDao;
	
	@Override
	public boolean canUpgradeLevel(User user) {
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

	@Override
	public void upgradeLevel(User user) {
		/* List 5-25
		if( user.getLevel() == Level.BASIC ) user.setLevel(Level.SILVER);
		else if( user.getLevel() == Level.SILVER) user.setLevel(Level.GOLD);
		userDao.update(user);
		 */
		user.upgradeLevel();
		userDao.update(user);
	}

}
