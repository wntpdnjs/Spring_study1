package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MessageVO;

@Repository


public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertM(MessageVO vo) {
		this.sqlSession.insert("m_in2",vo); //메세지 XML로 연결
		
	}//메세지 추가

}
