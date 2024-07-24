package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestVO {//tbl_test 테이블 컬럼명과 일치하는 데이터 저장빈 클래스 생성
	
	private int test_no;
	private String test_title;
	private String test_cont;

}
