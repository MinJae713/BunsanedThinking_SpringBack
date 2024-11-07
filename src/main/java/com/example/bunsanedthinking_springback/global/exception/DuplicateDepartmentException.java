package com.example.bunsanedthinking_springback.global.exception;

public class DuplicateDepartmentException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateDepartmentException() {
		super("중복된 부서 명이 존재합니다.");

	}
}
