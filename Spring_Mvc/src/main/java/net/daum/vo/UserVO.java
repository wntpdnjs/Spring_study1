package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserVO { //tbl_user 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장 빈 클래스 정의
	private String uid2;
	private String upw;
	private String uname;
	private int upoint;
	
	
	

}
