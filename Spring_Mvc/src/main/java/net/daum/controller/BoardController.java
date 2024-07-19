package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller
@RequestMapping("/board/*") //컨트롤러 자체 매핑주소 등록
public class BoardController {
   
   @Autowired
   private BoardService boardService;
   
   //게시판 글쓰기 폼
   @RequestMapping(value="/board_write",method=RequestMethod.GET) //get우로 접속하는 매핑주소 처리,board_write매핑주소 등록
   public void board_write(HttpServletRequest request, Model m) {
	   int page =1;
	   if(request.getParameter("page") != null) {
		   page =Integer.parseInt(request.getParameter("page"));
	   }
	   m.addAttribute("page", page); // page 키 이르ㅁ에 쪽번호 저장 -> 페이징에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능 구현
      //리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다. 뷰페이지 경로는 /WEB-INF/views/board_write.jsp   
   }//board_write()
   
   //게시판 저장
   @PostMapping("/board_write_ok") //POST로 접근하는 매핑주소를 처리,
   public String board_write_ok(BoardVO b) {
      /* board_write.jsp의 네임파라미터 이름과 BoardVO.java의 변수명이 같으면 BoardVO b객체에 글쓴이, 글제목, 글내용 값이 저장되어 있다.
       */
      this.boardService.insertBoard(b); //게시판 저장
      return "redirect:/board/board_list"; //게시판 목록보기 매핑주소로 이동s
   } //board_write_ok()
   
   //게시판 목록
   @GetMapping("/board_list")
   public ModelAndView board_list(BoardVO b, HttpServletRequest request) {
	   int page =1;
	   int limit =7; // 한 페이지에 보여지는 목록 개수
	   
	   if(request.getParameter("page") != null) {
		   page = Integer.parseInt(request.getParameter("page"));
	  
		   
	   }
	   b.setStartrow((page-1)*7+1);
	   b.setEndrow(b.getStartrow()+limit-1);
	   
	   int totalCount = this.boardService.getTotalCount();
	   
	   List<BoardVO> blist = this.boardService.getBoardList(b);
	   
	   //페이징 연산
	   int maxpage =(int)((double)totalCount/limit+0.95);
	   int startpage = (((int)((double)page/10+0.9))-1)*10+1;
	   int endpage = maxpage;
	   
	   if(endpage>startpage+10-1) endpage=startpage+10-1;
	   
	   
       ModelAndView listM=new ModelAndView("board/board_list"); //생성자 인자값으로 뷰페이지 경로 설정
       listM.addObject("totalCount", totalCount);
       listM.addObject("blist",blist); //blist키 이름에 목록을 저장
       listM.addObject("page", page);
       listM.addObject("maxpage", maxpage);
       listM.addObject("startpage", startpage);
       listM.addObject("endpage", endpage);
       
       return listM;
    }//board_list()
   
   
   //게시판 내용보기와 조회수증가, 수정폼
   @RequestMapping("/board_cont") //get or post로 접근하는 매핑주소를 처리,  board_cont매핑주소 등록
   public ModelAndView board_cont(@RequestParam("bno") int bno, int page, String state) {
      BoardVO bc =null;
      if(state.equals("cont")) {//내용보기일 때 실행
         bc=this.boardService.getBoardCont(bno); //내용보기와 조회수 증가 -> 내용보기 일 떄만 조회수 증가
         
      }
      else {//내용보기가 아닌 수정폼
    	  bc = this.boardService.getBoardCont2(bno); // 조회수 증가 안된 수정폼
      }
      ModelAndView cm = new ModelAndView();
      cm.addObject("page", page);
      cm.addObject("bc", bc);
      
      if(state.equals("cont")) {
    	  cm.setViewName("board/board_cont"); // 뷰리졸브(뷰페이지) 경로는 
      }else if (state.equals("edit")) {//수정폼
    	  cm.setViewName("board/board_edit");
      }
      		
      return cm;
   } //board_cont()
   
   
   //게시판 수정 완료
   @RequestMapping(value="/board_edit_ok",method=RequestMethod.POST) //POSt 로 저븐하는 매핑주소 철
   public ModelAndView board_edit_ok(BoardVO eb,int page) {
	   
	   this.boardService.editBoard(eb);
	   
	   ModelAndView em = new ModelAndView("redirect:/board/board_list");
	   em.addObject("page" , page); // board_list?page=쪽번호가 get 으로 전달된다. 
	   return em;
   }
   
   //게시판삭제
   @GetMapping("/board_del")
   public String board_del(int bno, int page, HttpServletRequest request) {
	  this.boardService.delBoard(bno);
	  
	  return "redirect:/board/board_list?page="+page;
   }
}
