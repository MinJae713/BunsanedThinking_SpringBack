package com.example.bunsanedthinking_springback.global.exception;

public class NotExistMaintainedContract extends Exception {
	private static final long serialVersionUID = 1L;

	public NotExistMaintainedContract() {
		super("이미 해지된 보험 계약입니다.");
	}
}
