package net.daum.vo;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ReplyVO {
	
	private int rno; // 댓글 번호
	private int bno; // 게시판 번호
	private String replyer; //댓글 작성자
	private String replytext;//댓글 내용
	private String regdate; //댓글 등록 날짜
	private String updatedate; // 댓글 수정 날짜
	
	
	

}
