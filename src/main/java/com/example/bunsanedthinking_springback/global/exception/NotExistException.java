package com.example.bunsanedthinking_springback.global.exception;

public class NotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotExistException() {
		super("정보가 존재하지 않습니다.");
	}

	public NotExistException(String msg) {
		super(msg);
	}
}
