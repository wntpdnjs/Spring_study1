package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDAO;
import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

   @Autowired
   private ReplyDAO replyDao;
   
   @Autowired
   private BoardDAO boardDao;
   
   
   // 댓글이 추가되면 댓글 수 1 증가 -> Spring AOP 통한 트랜젝션 적용 대상
   @Transactional // 트랜젝션 적용
   @Override
   public void insertReply(ReplyVO vo) {
      this.replyDao.insertReply(vo);
      this.boardDao.updateReplyCnt(vo.getBno(),1); //댓글이 추가되면 해당 게시판 번호에 대한 댓글 수 1 증가 
   }

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		
		
		this.replyDao.updateReply(vo);
		
	}
//댓글 삭제되면 댓그 수 1 감소 -> aop 를 통한 트렌젝션 적용 대상
	@Override
	@Transactional
	public void deleteReply(int rno) {
		int bno = this.replyDao.getBno(rno); // 댓글이 삭제되기 이전에 먼저 댓글 번호 기준으로 게시판 번호를 구함.
		this.replyDao.deleteReply(rno); //댓글이 삭제
		this.boardDao.updateReplyCnt(bno, -1); // 댓글이 삭제되면 게시판 번호에 대한 댓글 수 1 감소
		
	}
}
