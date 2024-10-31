package com.example.bunsanedthinking_springback.exception;

public class DuplicatePartnerCompanyException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicatePartnerCompanyException() {
		super("중복된 협력 업체 이름이 존재합니다.");
		
	}
}
