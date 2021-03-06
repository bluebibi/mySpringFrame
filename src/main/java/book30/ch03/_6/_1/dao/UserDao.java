package book30.ch03._6._1.dao;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import book30.ch03.domain.User;

public class UserDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		this.dataSource = dataSource;
	}

	public void add(final User user) throws ClassNotFoundException, SQLException {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id},
				new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
	}

	public void deleteAll() throws SQLException {
		/* List 3-46
		    this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("delete from users");
			}
		});
		*/
		this.jdbcTemplate.update("delete from users");
	}

	

	public int getCount() throws SQLException {
		return this.jdbcTemplate.queryForInt("select count(*) from users");
		/* List 3-49
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("select count(*) from users");
			}
			}, new ResultSetExtractor<Integer>() {
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					rs.next();
					return rs.getInt(1);
				}
		});
		*/
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id",
				new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
	}
}
