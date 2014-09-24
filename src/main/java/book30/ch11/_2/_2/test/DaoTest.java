package book30.ch11._2._2.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import book30.ch11._2._2.service.MemberService;
import book30.ch11.domain.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="../../applicationContext.xml")
public class DaoTest {
	@Autowired
	private MemberService memberService;
	
	@Before
	public void init() {
	    this.memberService.deleteAll();
	}

	@Test
	public void addTest() {
		//addMember		
		memberService.addMember1("001", "GilDong Hong", 90);
		memberService.addMember2("002", "ChulSoo Kim", 80);
		
		Map<String, Object> memberMap = new HashMap<String, Object>();
		memberMap.put("number", "003");
		memberMap.put("name", "SungHo Ahn");
		memberMap.put("point", 70);
		memberService.addMember3(memberMap);
		
		Member member = new Member("004", "YounSim Lee", 60);
		memberService.addMember4(member);

		//numMembers		
		assertThat(memberService.numMembers(), is(4));
	}
	
	@Test
	public void addAndGetKeyTest() {
		int id1 = memberService.addMemberAndGetKey1("001", "GilDong Hong", 90);
		Member m1 = memberService.getMember1("001");
		Member m1_2 = memberService.getMemberById1(id1);
		assertThat(m1.getId(), is(id1));
		assertThat(m1_2.getId(), is(id1));		
		
		
		int id2 = memberService.addMemberAndGetKey2("002", "ChulSoo Kim", 80);
		Member m2 = memberService.getMember3("002");
		Member m2_2 = memberService.getMemberById2(id2);
		assertThat(m2.getId(), is(id2));
		assertThat(m2_2.getId(), is(id2));
	}
	
	@Test
	public void queryTest() {
		memberService.addMember1("001", "GilDong Hong", 90);
		memberService.addMember2("002", "ChulSoo Kim", 80);
		
		//numMembers
		assertThat(memberService.numMembers(), is(2));
		
		//getMembersOverPoint
		assertThat(memberService.numMembersOverPoint1(78), is(2));
		assertThat(memberService.numMembersOverPoint2(78), is(2));
		
		//getMemberName, List 11-4
		assertThat(memberService.getMemberName1("001"), is("GilDong Hong"));
		assertThat(memberService.getMemberName2("001"), is("GilDong Hong"));
		assertThat(memberService.getMemberName1("003"), is(nullValue()));
		assertThat(memberService.getMemberName2("003"), is(nullValue()));
		
		//getMember
		Member member1 = memberService.getMember1("001");
		assertThat(member1.getName(), is("GilDong Hong"));
		
		Member member2 = memberService.getMember2("002");
		assertThat(member2.getName(), is("ChulSoo Kim"));	
		
		Member member3 = memberService.getMember3("001");
		assertThat(member3.getName(), is("GilDong Hong"));
		
		Member member4 = memberService.getMember4("002");
		assertThat(member4.getName(), is("ChulSoo Kim"));
		
		//getMemberMap
		Map<String, Object> memberMap1 = memberService.getMemberMap1("001");
		assertThat((String)memberMap1.get("name"), is("GilDong Hong"));
		
		Map<String, Object> memberMap2 = memberService.getMemberMap2("002");
		assertThat((String)memberMap2.get("name"), is("ChulSoo Kim"));
		
		//getMemberList
		List<Member> memberList1 = memberService.getMemberList1(78);
		assertThat(memberList1.size(), is(2));
		
		List<Member> memberList2 = memberService.getMemberList2(78);
		assertThat(memberList2.size(), is(2));
		
		List<Member> memberList3 = memberService.getMemberList3(78);
		assertThat(memberList3.size(), is(2));
		
		List<Member> memberList4 = memberService.getMemberList4(78);
		assertThat(memberList4.size(), is(2));
		
		//getMemberMapList
		List<Map<String, Object>> memberMapList1 = memberService.getMemberMapList1(78);
		assertThat(memberMapList1.size(), is(2));
		
		List<Map<String, Object>> memberMapList2 = memberService.getMemberMapList2(78);
		assertThat(memberMapList2.size(), is(2));
	}
	
	@Test
	public void batchUpdateTest() {
		List<Member> memberList1 = new ArrayList<Member>();
		memberList1.add(new Member("005", "Soohuyn Kim", 76));
		memberList1.add(new Member("006", "Jeain Moon", 99));
		memberList1.add(new Member("007", "HyunDo Ahn", 88));

		int[] addCount1 = this.memberService.addMemberList1(memberList1);
		assertArrayEquals(addCount1, new int[]{1,1,1});
		
		List<Map<String, Object>> memberMapList1 = new ArrayList<Map<String, Object>>();
		Map<String, Object> memberMap1_1 = new HashMap<String, Object>();
		memberMap1_1.put("point", 100);
		memberMap1_1.put("number", "005");
		memberMapList1.add(memberMap1_1);
		
		Map<String, Object> memberMap1_2 = new HashMap<String, Object>();
		memberMap1_2.put("point", 100);
		memberMap1_2.put("number", "006");
		memberMapList1.add(memberMap1_2);
		int[] updateCount1 = this.memberService.updateMemberList1(memberMapList1);
		assertArrayEquals(updateCount1, new int[]{1,1});
		
		Member member1 = memberService.getMember1("005");
		assertThat(member1.getPoint(), is(100));
		
				
		List<Member> memberList2 = new ArrayList<Member>();
		memberList2.add(new Member("008", "Sarang Kim", 65));
		memberList2.add(new Member("009", "Younghee Lee", 50));
		memberList2.add(new Member("010", "Soochung Kim", 99));
		
		int[] addCount2 = this.memberService.addMemberList2(memberList2);
		assertArrayEquals(addCount2, new int[]{1,1,1});
		
		List<Map<String, Object>> memberMapList2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> memberMap2_1 = new HashMap<String, Object>();
		memberMap2_1.put("point", 100);
		memberMap2_1.put("number", "008");
		memberMapList2.add(memberMap2_1);
		
		Map<String, Object> memberMap2_2 = new HashMap<String, Object>();
		memberMap2_2.put("point", 100);
		memberMap2_2.put("number", "009");
		memberMapList2.add(memberMap2_2);
		int[] updateCount2 = this.memberService.updateMemberList1(memberMapList2);
		assertArrayEquals(updateCount2, new int[]{1,1});
		
		Member member2 = memberService.getMember1("008");
		assertThat(member2.getPoint(), is(100));
	}
}