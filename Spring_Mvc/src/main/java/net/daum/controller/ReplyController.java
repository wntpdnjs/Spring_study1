package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.ReplyService;
import net.daum.vo.ReplyVO;

@RestController
@RequestMapping("/replies")

public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/addReply")
	public ResponseEntity<String> addReply(@RequestBody ReplyVO vo) {
		/*
		 * @RequestBody 는 전송된 키,값 쌍의 json 데이터를 ReplyVO 객체 타입으로 변환해 준다.
		 */
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService .insertReply(vo); // 댓 등록
			entity = new ResponseEntity<> ("SUCCESS", HttpStatus.OK); // 댓그 저장 성공 시 석세스랑 200 정상코드 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}//addReply
	

}
