package com.example.bunsanedthinking_springback.global.exception;

public class NotExistTerminatedContract extends Exception {
	private static final long serialVersionUID = 1L;

	public NotExistTerminatedContract() {
		super("해지된 보험 계약이 없습니다.");
	}
}
