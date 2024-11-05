package com.example.bunsanedthinking_springback.entity.counsel;

/**
 * @version 1.0
 * @created 27-5-2024
 */
public enum CounselProcessStatus {
	Completed(0, "처리완료"),
	Unprocessed(1, "미처리");

	private final int value;
	private String name;

	CounselProcessStatus(int value, String name) {
		this.value = value;
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
		for (CounselProcessStatus status : CounselProcessStatus.values()) {
			if (status.getValue() == number) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown CounselProcessStatus number: " + number);
	}

	// CounselProcessStatus의 인덱스를 반환하는 메서드
	public int getValue() {
		return this.value;
	}
}
