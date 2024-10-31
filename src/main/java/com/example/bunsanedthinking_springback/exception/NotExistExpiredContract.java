package com.example.bunsanedthinking_springback.exception;

public class NotExistExpiredContract extends Exception {
	private static final long serialVersionUID = 1L;
	public NotExistExpiredContract() {
		super("만기된 보험 계약이 없습니다.");
	}
}
