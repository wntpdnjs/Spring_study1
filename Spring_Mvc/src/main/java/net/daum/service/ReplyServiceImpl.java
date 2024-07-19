package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDAO;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

   @Autowired
   private ReplyDAO replyDao;
   
   @Override
   public void insertReply(ReplyVO vo) {
      this.replyDao.insertReply(vo);
   }

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.replyDao.listReply(bno);
	}
}
