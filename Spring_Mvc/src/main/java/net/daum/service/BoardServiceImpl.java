package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDAO;
import net.daum.vo.BoardVO;

@Service //@Service 애노테이션을 추가함으로써 스프링에서 서비스로 인식한다.
public class BoardServiceImpl implements BoardService {
   @Autowired
   private BoardDAO boardDao;

   @Override
   public void insertBoard(BoardVO b) {
      // TODO Auto-generated method stub
      this.boardDao.insertBoard(b);
      
   }
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return this.boardDao.getTotalCount();
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.boardDao.getBoardList(b);
	}

	@Override
	public BoardVO getBoardCont(int bno) {
		
		
		this.boardDao.updateHit(bno); //조회수 증가
		return this.boardDao.getBoardCont(bno);
	}

	@Override
	public BoardVO getBoardCont2(int bno) {
		// TODO Auto-generated method stub
		return this.boardDao.getBoardCont(bno);
	} //수정폼일 때는 조회수가 증가 안된 내용보기

	@Override
	public void editBoard(BoardVO eb) {
		this.boardDao.editBoard(eb);
	}

	@Override
	public void delBoard(int bno) {
		this.boardDao.delBoard(bno);
		
	}
   
   
}
