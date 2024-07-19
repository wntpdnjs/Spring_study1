package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO { // tbl_board 게시판 테이블 칼럼명과 일치하는 변수명을 정의
	
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;
	private String regdate;
	
	//페이징 쪽 나누기 관련변수
	private int startrow; //시작행 번호
	private int endrow; // 끝 행 번호

}
