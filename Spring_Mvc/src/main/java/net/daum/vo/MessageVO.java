package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MessageVO { //  tbl_message 테이블의 컬럼명과 일치하는 변수명을 가진 빈클래스를 정의
	
	private int mid;
	private String targetid;
	private String sender;
	private String message;
	private String senddate;
	

}
