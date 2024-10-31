package com.example.bunsanedthinking_springback.exception;

public class DuplicateResidentRegistrationNumberException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateResidentRegistrationNumberException() {
		super("중복된 주민등록번호가 존재합니다.");
		
	}
}
