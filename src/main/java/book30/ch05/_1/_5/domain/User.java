package book30.ch05._1._5.domain;

import book30.ch05._1._5.domain.User;

public class User {
	Level level;
	int login;
	int recommend;
	
	String id;
	String name;
	String password;
	
	public User(String id, String name, String password, Level level, int login, int recommend) {
		this.id = id;
		this.name = name;
		this.password= password;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
	}
	
	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public User() {
	}
	
	public void upgradeLevel() {
		Level nextLevel = this.level.nextLevel();
		if (nextLevel == null) {
			throw new IllegalStateException(this.level+ "is not able to be upgraded."); 
		} else {
			this.level = nextLevel;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
}
