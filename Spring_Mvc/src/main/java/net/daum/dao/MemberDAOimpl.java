package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository // Repository annotation을 설정해서 스프링에서 모델DAO로 인식한다
public class MemberDAOimpl implements MemberDAO {
	
	@Autowired //자동의존성 주입 
	private SqlSession sqlSession; // mybatis 쿼리문 생성할 sqlsession 생성

	@Override
	public void insertMember(MemberVO m) {
		
		this.sqlSession.insert("m_in",m);
		
		// TODO Auto-generated method stub
		
	}

}
