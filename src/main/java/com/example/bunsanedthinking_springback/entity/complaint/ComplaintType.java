package com.example.bunsanedthinking_springback.entity.complaint;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public enum ComplaintType {
	Service("서비스 민원"),
	Product("상품 민원"),
	Question("질문 민원"),
	Others("기타 민원");
	
	private String name;

	ComplaintType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}