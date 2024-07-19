package net.daum.vo;

public class ProductVO {//데이터 저장빈 클래스
	
	private String name;//상품이름
	private int price;//상품가격
	
	public ProductVO(String name,int price) {
		this.name=name;
		this.price=price;
	}//생성자 오버로딩

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}//값 반환 메서드=>getter()메서드
	
}
