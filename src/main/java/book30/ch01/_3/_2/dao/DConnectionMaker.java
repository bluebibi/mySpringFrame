package book30.ch01._3._2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		// D사의 독자적인 방법으로 Connection을 생성하는 코드
		return null;
	}
}
