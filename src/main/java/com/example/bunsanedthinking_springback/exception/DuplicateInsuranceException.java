package com.example.bunsanedthinking_springback.exception;

public class DuplicateInsuranceException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateInsuranceException() {
		super("중복된 보험상품명이 존재합니다.");
		
	}
	
}
