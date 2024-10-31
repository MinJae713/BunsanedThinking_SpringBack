package com.example.bunsanedthinking_springback.exception;

public class DuplicateOfficeSupplyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateOfficeSupplyException() {
		super("중복된 집기 비품 명이 존재합니다.");
		
	}
}
