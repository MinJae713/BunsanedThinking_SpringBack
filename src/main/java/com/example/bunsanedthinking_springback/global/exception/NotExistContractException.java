package com.example.bunsanedthinking_springback.global.exception;

public class NotExistContractException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotExistContractException() {
		super("기가입 보험 상품 정보가 존재하지 않습니다.");

	}
}
