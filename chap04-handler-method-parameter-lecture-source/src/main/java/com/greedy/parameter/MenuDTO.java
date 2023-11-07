package com.greedy.parameter;

/* @ModelAttribute를 컨트롤러에서 사용하기 위해서는 DTO에서 유의해야 할 사항들이 있다. */
public class MenuDTO {
	
	/* 1. DTO의 필드 값들을 form 태그 안에 있는 input태그들의 name들과 일치하게 만들어야 한다. */
	private String name;
	private int price;
	private int categoryCode;
	private String orderableStatus;
	
	/* 2. 커맨드 객체는 기본 생성자를 이용하여 인스턴스를 만들기 때문에 반드시 기본 생성자가 필요하다. */
	public MenuDTO() {
	}
	public MenuDTO(String name, int price, int categoryCode, String orderableStatus) {
		this.name = name;
		this.price = price;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
	}
	
	/*
	 *  3. 요청 파라미터의 name과 일치하는 필드에 setter를 이용하는 것이기 때문에 setter가 작성되어야 하는 것은 물론이고
	 *     setter 메소드가 필드에 맞는 네이밍 규칙에 맞게 작성 되어야 한다.
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getOrderableStatus() {
		return orderableStatus;
	}
	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}
	
	@Override
	public String toString() {
		return "MenuDTO [name=" + name + ", price=" + price + ", categoryCode=" + categoryCode + ", orderableStatus="
				+ orderableStatus + "]";
	}
}
