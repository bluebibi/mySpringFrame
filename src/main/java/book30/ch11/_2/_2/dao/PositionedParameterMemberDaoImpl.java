package book30.ch11._2._2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import book30.ch11.domain.Member;
import book30.ch11.domain.MemberRowMapper;

public class PositionedParameterMemberDaoImpl {
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//deleteAll		
	public void deleteAll() {
		//public int update(String sql) throws DataAccessException
		this.jdbcTemplate.update("DELETE FROM MEMBERS");
	}
	
	//addMember
	public void addMember1(final String number, final String name, final int point) {
		//public int update(String sql, Object... args) throws DataAccessException
		this.jdbcTemplate.update("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(?, ?, ?)", number, name, point);
	}
	
	//addMemberAndGetKey
	public int addMemberAndGetKey1(final String number, final String name, final int point) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		//public int update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder) throws DataAccessException
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, number);
				ps.setString(2, name);
				ps.setInt(3, point);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	//numMembers
	public int numMembers() {
		//public <T> T queryForObject(String sql, Class<T> requiredType)
		return this.jdbcTemplate.queryForObject("SELECT count(*) FROM MEMBERS", Integer.class);
	}

	//numMembersOverPoint
	public int numMembersOverPoint1(final int point) {
		//public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException
		return this.jdbcTemplate.queryForObject("SELECT count(*) FROM MEMBERS WHERE POINT > ?", Integer.class, point);
	}
	
	//getMemberName, List 11-4
	public String getMemberName1(final String number) {
		try {
			//public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException
			return this.jdbcTemplate.queryForObject("SELECT NAME FROM MEMBERS WHERE NUMBER = ?", String.class, number);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMember
	public Member getMember1(final String number) {
		try {
			//public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException
			return this.jdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE NUMBER = ?", 
													new BeanPropertyRowMapper<Member>(Member.class), number);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Member getMember2(final String number) {
		try {
			//public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException
			return this.jdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE NUMBER = ?", this.memberRowMapper, number);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberById
	public Member getMemberById1(final int id) {
		try {
			//public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException
			return this.jdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE ID = ?", this.memberRowMapper, id);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberMap (note: get a single member)
	public Map<String, Object> getMemberMap1(final String number) {
		try {
			//public Map<String,Object> queryForMap(String sql, Object... args) throws DataAccessException
			return this.jdbcTemplate.queryForMap("SELECT * FROM MEMBERS WHERE NUMBER = ?", number);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberList
	public List<Member> getMemberList1(final int point) {
		//public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException
		return this.jdbcTemplate.query("SELECT * FROM MEMBERS WHERE POINT > ?", 
											new BeanPropertyRowMapper<Member>(Member.class), point);
	}
	
	public List<Member> getMemberList2(final int point) {
		//public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException		
		return this.jdbcTemplate.query("SELECT * FROM MEMBERS WHERE POINT > ?", this.memberRowMapper, point);
	}
	
	//getMemberMapList
	public List<Map<String, Object>> getMemberMapList1(final int point) {
		//public List<Map<String,Object>> queryForList(String sql, Object... args) throws DataAccessException
		return this.jdbcTemplate.queryForList("SELECT * FROM MEMBERS WHERE POINT > ?", point);
	}
	
	//addMemberList
	public int[] addMemberList1(final List<Member> memberList) {
		//public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException
		int[] updateCount = this.jdbcTemplate.batchUpdate("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(?, ?, ?)", 
										new BatchPreparedStatementSetter() {
											@Override
											public void setValues(PreparedStatement ps, int i) throws SQLException {
												Member member = memberList.get(i);
												ps.setString(1, member.getNumber());
												ps.setString(2, member.getName());
												ps.setInt(3, member.getPoint() );
											}
										 
											@Override
											public int getBatchSize() {
												return memberList.size();
											}
										});
		return updateCount;
	}
	
	//updateMemberList
	public int[] updateMemberList1(final List<Map<String, Object>> memberMapList) {
		//public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException
		int[] updateCount = this.jdbcTemplate.batchUpdate("UPDATE MEMBERS SET POINT = ? WHERE NUMBER = ?", 
										new BatchPreparedStatementSetter() {
											@Override
											public void setValues(PreparedStatement ps, int i) throws SQLException {
												Map<String, Object> memberMap = memberMapList.get(i);
												ps.setInt(1, (Integer)memberMap.get("point"));
												ps.setString(2, (String)memberMap.get("number"));
											}
										 
											@Override
											public int getBatchSize() {
												return memberMapList.size();
											}
										});
		return updateCount;
	}
}