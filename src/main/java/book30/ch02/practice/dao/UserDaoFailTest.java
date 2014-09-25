package book30.ch02.practice.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="../applicationContext.xml")
public class UserDaoFailTest {
	@Autowired
	private UserDao dao;
	
	static UserDao testUserDao;
	
	@Before
	public void setUp() {
		testUserDao = this.dao;
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		assertThat(testUserDao, is(this.dao));
		assertTrue(UserDaoTest.testUserDao == null || UserDaoTest.testUserDao == UserDaoFailTest.testUserDao);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
}
