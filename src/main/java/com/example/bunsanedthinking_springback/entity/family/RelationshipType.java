package com.example.bunsanedthinking_springback.entity.family;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum RelationshipType {
	Mother("엄마"), Father("아빠"), Sister("여형제"), Brother("남형제"), Son("아들"), Daughter("딸");

	private String name;

	RelationshipType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}