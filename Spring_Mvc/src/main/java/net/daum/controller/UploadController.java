package net.daum.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import net.daum.service.TestService;
import net.daum.vo.TestVO;

@Controller
public class UploadController {
	
	
    @Autowired
    private TestService testService;
   
   // 동기식 파일첨부 뷰페이지
   @GetMapping("/uploadForm")
   public void uploadForm() {
      // 리턴 타입이 없는 void 형이면 매핑주소인 uploadForm이 뷰페이지 파일명이 됨
   }// uploadForm()
   
   @PostMapping("/uploadFormAction")
   public void uploadFormAction(MultipartFile[] uploadFile, HttpServletRequest request) {
      String uploadFolder = request.getSession().getServletContext().getRealPath("upload\\"); // 첨부된 실제 이진파일이 업로드될 경로
      System.out.println("실제 업로드 경로 : " + uploadFolder);
      
      for(MultipartFile multipartFile : uploadFile) {
         System.out.println("===========================>");
         System.out.println("upload file name : " + multipartFile.getOriginalFilename());
         System.out.println("upload file size : " + multipartFile.getSize());
         
         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
                  
         try {
            multipartFile.transferTo(saveFile);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
     
   }
   @RequestMapping(value="/uploadaAjax", method=RequestMethod.GET)
   public ModelAndView uploadAjax() {
 	  return new ModelAndView ("uploadAjaxForm");
   }//uploadAjax
   
   @RequestMapping(value="/uploadAjaxAction", method=RequestMethod.POST) //포스트로 접근하는 매핑주소 처리
   public void uploadAjaxAction(MultipartFile[] uploadFile, HttpServletRequest request) {
	   
	   System.out.println("\n =====================> upload Ajax post ....");
	   String uploadFolder = request.getSession().getServletContext().getRealPath("upload");
	   
	   for(MultipartFile multipartFile : uploadFile) {
		   System.out.println("\n ============= \n");
		   System.out.println("Upload File Name : "+multipartFile.getOriginalFilename());
		   System.out.println("Upload File Size : "+ multipartFile.getSize());
		   
		   String uploadFileName = multipartFile.getOriginalFilename();
		   uploadFileName= uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		   /*
		    * 인터넷 익스플로워인 경우는 첨부파일 전체경로가 전송된다. 경로구분 '\'을 lastindexof()메서드에 의해서 맨 오른쪽부터 찾아서 가장 먼저 나오는
		    * '\'의 위치번호를 맨 왼쪽 0부터 시작해서 구한다. 
		    */
		   System.out.println("only file name : "+uploadFileName);
		   
		   File saveFile = new File(uploadFolder, uploadFileName);
		   
		   try {
			   multipartFile.transferTo(saveFile);
			   
		   } catch (Exception e) {
	            e.printStackTrace();}
		   
	   }//for
   }//uploadAjaxAction()
   
   @GetMapping("/test_write")
   public String test_write() {
      return "test/test_write";
   }
   @PostMapping("/test_ok")
   public ModelAndView test_ok(TestVO vo) {
	   
	   this.testService.test_insert(vo);
	   
	   
    

       // 저장이 완료된 후 리디렉션할 페이지를 반환합니다.
       return null;
   }
////    @RequestMapping(value="/board_write",method=RequestMethod.GET) //get우로 접속하는 매핑주소 처리,board_write매핑주소 등록
//   public void board_write(HttpServletRequest request, Model m) {
//	
//   }//board_write()
//   
//   //게시판 저장
//   @PostMapping("/board_write_ok") //POST로 접근하는 매핑주소를 처리,
//   public String board_write_ok(BoardVO b) {
//      /* board_write.jsp의 네임파라미터 이름과 BoardVO.java의 변수명이 같으면 BoardVO b객체에 글쓴이, 글제목, 글내용 값이 저장되어 있다.
//       */
//      this.boardService.insertBoard(b); //게시판 저장
//      return "redirect:/board/board_list"; //게시판 목록보기 매핑주소로 이동s
//   } //board_write_ok()
//   
//   
   
   
  
}