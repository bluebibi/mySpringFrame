package book30.ch02._3._5.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import book30.ch02._3._5.domain.User;


public class UserDaoFailTest {
	private UserDao dao;
	
	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("book30/ch02/_3/_5/applicationContext.xml");
		this.dao = context.getBean("userDao", UserDao.class);
		
		this.dao.deleteAll();
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		

		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
}
