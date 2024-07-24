package net.daum.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class PointDAOImpl implements PointDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {
		Map<String, Object> pm= new HashMap<>();
		pm.put("sender", sender); // 키, 값 쌍으로 저장, 보낸 사람을 저장 
		pm.put("point", point); // 포인트 점수
		this.sqlSession.update("pointUp", pm); // pointup 은 point.xml 에서 설정할 유일 아이디 명
		
	}

}
