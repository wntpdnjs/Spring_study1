package net.daum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;

@SpringBootTest
public class MemberDAOTest {
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Test

	public void testInsertMember() throws Exception{
		MemberVO m = new MemberVO();
		m.setUserid("kkkk");
		m.setUserpw("77777");
		m.setUsername("주세원");
		m.setEmail("Kkkk@naver.com");
		
		this.memberDAO.insertMember(m);
	}

}
