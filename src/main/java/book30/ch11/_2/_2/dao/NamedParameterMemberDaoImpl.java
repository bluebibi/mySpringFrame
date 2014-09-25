package book30.ch11._2._2.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import book30.ch11.domain.Member;
import book30.ch11.domain.MemberRowMapper;

public class NamedParameterMemberDaoImpl {
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//addMember
	public void addMember2(final String number, final String name, final int point) {
		//public int update(String sql, SqlParameterSource paramSource) throws DataAccessException
		this.namedParameterJdbcTemplate.update("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(:number, :name, :point)", 
												new MapSqlParameterSource().addValue("number", number)
																		   .addValue("name", name)
																		   .addValue("point", point));
	}
	
	public void addMember3(final Map<String, Object> memberMap) {
		//public int update(String sql, Map<String,?> paramMap) throws DataAccessException
		this.namedParameterJdbcTemplate.update("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(:number, :name, :point)", memberMap);
	}
	
	public void addMember4(final Member member) {
		//public int update(String sql, SqlParameterSource paramSource) throws DataAccessException
		this.namedParameterJdbcTemplate.update("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(:number, :name, :point)", 
												new BeanPropertySqlParameterSource(member));
	}
	
	//addMemberAndGetKey
	public int addMemberAndGetKey2(final String number, final String name, final int point) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		//public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder) throws DataAccessException
		namedParameterJdbcTemplate.update("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(:number, :name, :point)", 
											new MapSqlParameterSource().addValue("number", number).addValue("name", name).addValue("point", point),
											keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	//numMembersOverPoint
	public int numMembersOverPoint2(final int point) {
		//public <T> T queryForObject(String sql, SqlParameterSource paramSource, Class<T> requiredType) throws DataAccessException
		return this.namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM MEMBERS WHERE POINT > :point", 
																new MapSqlParameterSource("point", point), Integer.class);
	}
	
	//getMemberName, List 11-4
	public String getMemberName2(final String number) {
		try {
			//public <T> T queryForObject(String sql, SqlParameterSource paramSource, Class<T> requiredType) throws DataAccessException
			return this.namedParameterJdbcTemplate.queryForObject("SELECT NAME FROM MEMBERS WHERE NUMBER = :number", 
														new MapSqlParameterSource("number", number), String.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	//getMember
	public Member getMember3(final String number) {
		try {
			//public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException
			return this.namedParameterJdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE NUMBER = :number", 
														new MapSqlParameterSource("number", number), 
														new BeanPropertyRowMapper<Member>(Member.class));
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Member getMember4(final String number) {
		try {
			//public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException
			return this.namedParameterJdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE NUMBER = :number", 
														new MapSqlParameterSource("number", number), 
														this.memberRowMapper);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberById
	public Member getMemberById2(final int id) {
		try {
			//public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException
			return this.namedParameterJdbcTemplate.queryForObject("SELECT * FROM MEMBERS WHERE ID = :id", 
														new MapSqlParameterSource("id", id), 
														this.memberRowMapper);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberMap (note: get a single member)
	public Map<String, Object> getMemberMap2(final String number) {
		try {
			//public Map<String,Object> queryForMap(String sql, SqlParameterSource paramSource) throws DataAccessException
			return this.namedParameterJdbcTemplate.queryForMap("SELECT * FROM MEMBERS WHERE NUMBER = :number", 
																new MapSqlParameterSource("number", number));
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//getMemberList
	public List<Member> getMemberList3(final int point) {
		//public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException
		return this.namedParameterJdbcTemplate.query("SELECT * FROM MEMBERS WHERE POINT > :point", 
														new MapSqlParameterSource("point", point), 
														new BeanPropertyRowMapper<Member>(Member.class));
	}
	
	public List<Member> getMemberList4(final int point) {
		//public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException
		return this.namedParameterJdbcTemplate.query("SELECT * FROM MEMBERS WHERE POINT > :point", 
														new MapSqlParameterSource("point", point), 
														this.memberRowMapper);
	}
	
	//getMemberMapList
	public List<Map<String, Object>> getMemberMapList2(final int point) {
		//public List<Map<String,Object>> queryForList(String sql, SqlParameterSource paramSource) throws DataAccessException
		return this.namedParameterJdbcTemplate.queryForList("SELECT * FROM MEMBERS WHERE POINT > :point", 
														new MapSqlParameterSource("point", point));
	}
	
	//addMemberList
	public int[] addMemberList2(final List<Member> memberList) {
		SqlParameterSource[] sqlParameterSource = new SqlParameterSource[memberList.size()];
		int i = 0;
		for (Member member: memberList) {
			sqlParameterSource[i] = new BeanPropertySqlParameterSource(member);
			i++;
		}
		//public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs)
		int[] updateCount = this.namedParameterJdbcTemplate.batchUpdate("INSERT INTO MEMBERS(NUMBER, NAME, POINT) VALUES(:number, :name, :point)", sqlParameterSource);
		return updateCount;
	}
	
	//udpateMemberList
	public int[] udpateMemberList2(final List<Map<String, Object>> memberMapList) {
		SqlParameterSource[] sqlParameterSource = new SqlParameterSource[memberMapList.size()];
		int i = 0;
		for (Map<String, Object> memberMap : memberMapList) {
			sqlParameterSource[i] = new MapSqlParameterSource()
										.addValue("point", memberMap.get("point"))
										.addValue("number", memberMap.get("number"));
			i++;
		}
		//public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs)
		int[] updateCount = this.namedParameterJdbcTemplate.batchUpdate("UPDATE MEMBERS SET POINT = :point WHERE NUMBER = :number", sqlParameterSource);
		return updateCount;
	}
}