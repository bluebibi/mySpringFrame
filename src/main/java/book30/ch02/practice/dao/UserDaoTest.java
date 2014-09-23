package book30.ch02.practice.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import book30.ch02.practice.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="../test-applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private ApplicationContext context;
	
	static UserDao testUserDao;
	
	@Before
	public void setUp() {
		testUserDao = this.dao;
	}

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		assertThat(testUserDao, is(this.dao));
		assertTrue(UserDaoFailTest.testUserDao == null || UserDaoFailTest.testUserDao == UserDaoTest.testUserDao);
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		assertThat(testUserDao, is(this.dao));
		assertTrue(UserDaoFailTest.testUserDao == null || UserDaoFailTest.testUserDao == UserDaoTest.testUserDao);
	}
	
	@Test
	public void compareBean() {
		assertThat(testUserDao, is(this.dao));
		assertTrue(UserDaoFailTest.testUserDao == null || UserDaoFailTest.testUserDao == UserDaoTest.testUserDao);
		
		UserDao dao2 = context.getBean("userDao", UserDao.class);
		assertThat(dao2, is(this.dao));		
	}
}
