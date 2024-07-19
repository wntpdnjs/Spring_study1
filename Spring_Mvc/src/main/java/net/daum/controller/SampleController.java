package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
 * STS(이클립스) 개발툴에서 자동 임포트 단축키는 ctrl+shift+영문자 o
 */
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.daum.vo.ProductVO;

@Controller //@Controller 애노테이션을 추가함으로써 스프링에서 컨트롤러로 인식하게 한다.
public class SampleController {

	@RequestMapping("/doA") // doA매핑주소 등록.매핑주소란 브라우저 주소창에서 실행되는 주소값이다. @RequestMapping은 get or post로 접근하는 매핑주소를 처리
	public void doA(Model m) {
		//리턴타입이 없는 void형이면 매핑주소인 doA가 뷰페이지 파일명이 된다. 뷰리졸브 경로(뷰페이지 경로)는 /WEB-INF/views/doA.jsp
		
		m.addAttribute("doA_key", "doA.jsp가 실행됨");//doA_key이름에 값을 저장함.
		System.out.println("doA 매핑주소가 실행되었다.");
	}//doA()
	
	@GetMapping("/doC") //@GetMapping 은 get으로 접근하는 매핑주소를 처리, doA매핑주소 등록
	public String doC(@ModelAttribute("msg") String name) {
		/* @ModelAttribute("msg")는 msg 피라미터이름에 인자값을 담아서 전달한다. 웹주소에서 실행되는 매핑주소값으로
		 *  doC?msg=인자값 형태의 웹주소창에 노출되는 get방
		 * 식으로 전달해야 한다. 보안상 좋지 않다.
		 */
		
		return "result";// 뷰페이지 경로는 /WEB-INF/views/result.jsp
	}//doC()
	
	@GetMapping("/nameprice") //nameprice매핑주소 등록
	public ModelAndView nameprice() {
		
		ProductVO p=new ProductVO("냉장고",2540000);
		
		ModelAndView pm=new ModelAndView();
		pm.addObject("p",p);//p키이름에 p객체 저장
		pm.setViewName("shop/pro_name");//뷰리졸브 경로는 /WEB-INF/views/shop/pro_name.jsp
		return pm;
	}//nameprice()
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg2","홍길동");
		/* 다른 매핑주소로 msg2키이름에 홍길동을 담아서 전달한다. 백엔드 서버단에서 실행되기 때문에 주소창에 노출 안된다. 보안상 좋다. 
		 */
		return "redirect:/doF";//doE매핑주소가 실행되면 doF매핑주소로 이동한다. redirect로 이동하는 방식은 get이다.
	}//doE()
	
	@GetMapping("/doF")
	public void doF(@ModelAttribute("msg2") String name) {
		System.out.println("전달된 값:"+name);
	}//doF()
	
	//키 값 쌍의 json 데이터 생성실습
	@GetMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		//responseBody 쓰면 JSP 파일 만들지 않고도 키,값 쌍의 json 데이터 만들 수 있고
		//메서드 리턴타입이 Product빈 타입이면 해당 빈 클래스의 변수명이 json 데이터의 키 이름이 된다
		ProductVO p=new ProductVO("수박",15000);
		return p;
	}
}










