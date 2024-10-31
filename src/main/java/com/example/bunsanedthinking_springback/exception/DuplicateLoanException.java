package com.example.bunsanedthinking_springback.exception;

public class DuplicateLoanException extends Exception{

	private static final long serialVersionUID = 1L;

	public DuplicateLoanException() {
		super("중복된 대출 상품명이 존재합니다.");
		
	}
}
