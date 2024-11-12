package com.example.bunsanedthinking_springback.global.exception;

public class AlreadyRequestingException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyRequestingException() {
		super("이미 신청한 상태입니다.");
	}
}
