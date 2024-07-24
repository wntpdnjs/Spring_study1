package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
	//비동ㄱ식 아작소 댓글 
	@RequestMapping("/test")
	public void test() {
		//리턴 타입 없는 void 이면 test 가 뷰페이지 명이 된다 . 뷰페이지 경로는 /WEB-INF/views/test.jsp
	}


}
