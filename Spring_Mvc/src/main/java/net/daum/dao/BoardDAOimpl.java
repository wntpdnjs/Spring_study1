package net.daum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository
public class BoardDAOimpl implements BoardDAO {
   
   @Autowired
   private SqlSession sqlSession;
   
   @Override
   public void insertBoard(BoardVO b) {
      this.sqlSession.insert("board_in",b); //mybatis에서 insert()메서드는 레코드를 저장하고, board_in은 board.xml에서 설정할
      //유일 아이디명이다.
      
   }
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("b_count");
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("b_list",b); //mybatis 에서 selecList() 메서드는 하나 이상의 레코드를 반환한다.
		//b_list 는 board.xml 에서 설정할 유일 아이디명이다.
	}//게시물 목록

	@Override
	public void updateHit(int bno) {
		this.sqlSession.update("b_hit",bno); //mybatis 에서 update() 메서드는 레코드를 수정한다. b_hit 는 board_xml 에서 설정할 유일 아이디명이다.
		
	}//조회수 증가

	@Override
	public BoardVO getBoardCont(int bno) {
		return this.sqlSession.selectOne("b_cont", bno);
		
	}//번호에 해다하는 내용보기

	@Override
	public void editBoard(BoardVO eb) {
		
		this.sqlSession.update("b_edit",eb);
	}//게시판 ㅜㅅ정

	@Override
	public void delBoard(int bno) {
		this.sqlSession.delete("b_del",bno); // mybatis 에서 delete()메소드는 레코드 삭제. b_del 은 board.xml에서 설정한 유일한 아이디 명이다. 

		
	}//게시판 삭제

	@Override
	public void updateReplyCnt(int bno, int count) {
		Map<String,Object> pm=new HashMap<>();
		
		pm.put("bno", bno); //게시판 번호 저장
		pm.put("count", count); //댓글 수 +1, -1
		this.sqlSession.update("upReplyCnt",pm);
	}//댓글 수 1증가, 감소
   
	
   
}
