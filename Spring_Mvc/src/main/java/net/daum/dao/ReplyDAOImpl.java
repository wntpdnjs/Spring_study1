package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
   
   @Autowired
   private SqlSession sqlSession;
   
}