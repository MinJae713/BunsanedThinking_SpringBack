package com.example.bunsanedthinking_springback.entity.counsel;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public enum CounselProcessStatus {
	Completed("처리완료"),
	Unprocessed("미처리");

	private String name;

	CounselProcessStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 번호에 따라 CounselProcessStatus 반환하는 메서드
	public static CounselProcessStatus fromInt(int number) {
		switch (number) {
			case 1:
				return Completed;
			case 2:
				return Unprocessed;
			default:
				throw new IllegalArgumentException("Unknown CounselProcessStatus number: " + number);
		}
	}

	// CounselProcessStatus의 인덱스를 반환하는 메서드
	public int getValue() {
		switch (this) {
			case Completed:
				return 1;
			case Unprocessed:
				return 2;
			default:
				throw new IllegalArgumentException("Unknown CounselProcessStatus: " + this);
		}
	}
}
