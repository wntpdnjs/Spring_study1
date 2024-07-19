package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter()메서드 자동생성
@Getter //getter()메서드 자동생성
public class SampleVO {
   
   private int mno;
   private String firstName;//성
   private String lastName;//이름
}