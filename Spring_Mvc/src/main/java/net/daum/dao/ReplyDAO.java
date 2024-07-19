package net.daum.dao;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyDAO {

	void insertReply(ReplyVO vo);

	List<ReplyVO> listReply(int bno);

}
