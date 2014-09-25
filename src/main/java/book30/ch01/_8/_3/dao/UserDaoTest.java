package book30.ch01._8._3.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import book30.ch01._8._1.dao.UserDao;
import book30.ch01.domain.User;


public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//class
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		//xml
		ApplicationContext context = new GenericXmlApplicationContext("book30/ch01/_8/_3/applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whites2hip");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "조회 성공");
<<<<<<< HEAD
		
		Integer a = 100;
		
=======
>>>>>>> 47980ae357dd0ba1260b847a48538951be920afa
	}
}
