package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
   
   @Autowired
   private SqlSession sqlSession;

   @Override
   public void insertReply(ReplyVO vo) {
      this.sqlSession.insert("reply_in",vo); //mybatis에서 insert()메서드는 레코드를 저장하고, reply_in은 mybatos 매퍼 xml
      //태그에서 유일아이디명이다.   
   }//댓글등록

	@Override
	public List<ReplyVO> listReply(int bno) {
	
		return this.sqlSession.selectList("reply_list", bno); //mybatis 에서 selectlist()메서드는 하나 이상의 레코드를 검색해서 복수개의 레코드들을 반환한다
// reply_list 는 mybatis 매퍼태그에서 설정할 유일 아이디명이다. 
	}//게시판 번호에 해당하는 댓글 모록 
   
}
