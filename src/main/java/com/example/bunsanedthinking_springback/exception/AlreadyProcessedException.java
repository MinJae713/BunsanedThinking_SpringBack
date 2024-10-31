package com.example.bunsanedthinking_springback.exception;

public class AlreadyProcessedException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyProcessedException() {
		super("이미 처리된 상태입니다.");
	}

	public AlreadyProcessedException(String message) {
		super(message);
	}
}
