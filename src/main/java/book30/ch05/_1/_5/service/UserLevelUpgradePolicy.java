package book30.ch05._1._5.service;

import book30.ch05._1._5.domain.User;

public interface UserLevelUpgradePolicy {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
}
