package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.TestDAO;
import net.daum.vo.TestVO;

@Service 

public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDAO testDao;

	@Override
	public void test_insert(TestVO vo) {
		this.testDao.test_insert(vo);
		
	}
	

}
