package book30.ch11._2._2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import book30.ch11._2._2.dao.PositionedParameterMemberDaoImpl;
import book30.ch11._2._2.dao.NamedParameterMemberDaoImpl;
import book30.ch11.domain.Member;

public class MemberService {
	@Autowired
	private PositionedParameterMemberDaoImpl memberDao;
	
	@Autowired
	private NamedParameterMemberDaoImpl namedParameterMemberDao;
	
	//deleteAll	
	public void deleteAll() {
		this.memberDao.deleteAll();
	}
	
	//addMember
	public void addMember1(String number, String name, int point) {
		this.memberDao.addMember1(number, name, point);
	}
	
	public void addMember2(String number, String name, int point) {
		this.namedParameterMemberDao.addMember2(number, name, point);
	}
	
	public void addMember3(Map<String, Object> memberMap) {
		this.namedParameterMemberDao.addMember3(memberMap);
	}
	
	public void addMember4(Member member) {
		this.namedParameterMemberDao.addMember4(member);
	}
	
	//addMemberAndGetKey
	public int addMemberAndGetKey1(String number, String name, int point) {
		return this.memberDao.addMemberAndGetKey1(number, name, point);
	}
	
	public int addMemberAndGetKey2(String number, String name, int point) {
		return this.namedParameterMemberDao.addMemberAndGetKey2(number, name, point);
	}
	
	//numMembers
	public int numMembers() {
		return this.memberDao.numMembers();
	}
	
	//numMembersOverPoint
	public int numMembersOverPoint1(int point) {
		return this.memberDao.numMembersOverPoint1(point);
	}
	
	public int numMembersOverPoint2(int point) {
		return this.namedParameterMemberDao.numMembersOverPoint2(point);
	}
	
	//getMemberName, List 11-4
	public String getMemberName1(String number) {
		return this.memberDao.getMemberName1(number);
	}
	
	public String getMemberName2(String number) {
		return this.namedParameterMemberDao.getMemberName2(number);
	}
	
	//getMember
	public Member getMember1(String number) {
		return this.memberDao.getMember1(number);
	}
	
	public Member getMember2(String number) {
		return this.memberDao.getMember2(number);
	}
	
	public Member getMember3(String number) {
		return this.namedParameterMemberDao.getMember3(number);
	}
	
	public Member getMember4(String number) {
		return this.namedParameterMemberDao.getMember4(number);
	}
	
	//getMemberById
	public Member getMemberById1(int id) {
		return this.memberDao.getMemberById1(id);
	}
	
	public Member getMemberById2(int id) {
		return this.namedParameterMemberDao.getMemberById2(id);
	}
	
	//getMemberMap
	public Map<String, Object> getMemberMap1(String number) {
		return this.memberDao.getMemberMap1(number);
	}
	
	public Map<String, Object> getMemberMap2(String number) {
		return this.namedParameterMemberDao.getMemberMap2(number);
	}
	
	//getMemberList
	public List<Member> getMemberList1(int point) {
		return this.memberDao.getMemberList1(point);
	}
	
	public List<Member> getMemberList2(int point) {
		return this.memberDao.getMemberList2(point);
	}
	
	public List<Member> getMemberList3(int point) {
		return this.namedParameterMemberDao.getMemberList3(point);
	}
	
	public List<Member> getMemberList4(int point) {
		return this.namedParameterMemberDao.getMemberList4(point);
	}
	
	//getMemberMapList
	public List<Map<String, Object>> getMemberMapList1(int point) {
		return this.memberDao.getMemberMapList1(point);
	}

	public List<Map<String, Object>> getMemberMapList2(int point) {
		return this.namedParameterMemberDao.getMemberMapList2(point);
	}
	
	//addMemberList
	public int[] addMemberList1(List<Member> memberList) {
		return this.memberDao.addMemberList1(memberList);
	}
	
	public int[] addMemberList2(List<Member> memberList) {
		return this.namedParameterMemberDao.addMemberList2(memberList);
	}
	
	//updateMemberList
	public int[] updateMemberList1(List<Map<String, Object>> memberMapList) {
		return this.memberDao.updateMemberList1(memberMapList);
	}
	
	public int[] updateMemberList2(List<Map<String, Object>> memberMapList) {
		return this.namedParameterMemberDao.udpateMemberList2(memberMapList);
	}
}