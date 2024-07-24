package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	
	//메시지 추가
	@PostMapping("/insertMessage")
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		ResponseEntity<String> entity=null;
		
		try {
			this.messageService.insertM(vo);//메세지 추가
			entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);//댓글 저장 성공시 SUCCESS문자와 200 정상상태 코드 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//예외 에러가 발생하면 에어 메시지와 나쁜상태 코드가 반환
		}return entity;
	}
	
	

}
