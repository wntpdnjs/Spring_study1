package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO replyDao;

}
