package com.example.bunsanedthinking_springback.global.constants.service.customer.dto;

public class HumanResourceDTOConstants {
    public static final String DEPARMENT_ID_POSITIVE_MESSAGE = "잘못된 부서 번호입니다.";

    public static final String NAME_SIZE_MESSAGE = "이름이 20글자를 초과하였습니다.";

    public static final String EMPLOYEE_POSITION_NOT_NULL_MESSAGE = "직급은 필수 입력 항목입니다.";

    public static final String ADDRESS_NOT_BLANK_MESSAGE = "주소는 필수 입력 항목입니다.";
    public static final String ADDRESS_SIZE_MESSAGE = "주소가 50글자를 초과하였습니다.";

    public static final String BANK_NAME_NOT_BLANK_MESSAGE = "은행명은 필수 입력 항목입니다.";
    public static final String BANK_NAME_SIZE_MESSAGE = "은행명이 10글자를 초과하였습니다.";

    public static final String BANK_ACCOUNT_NOT_BLANK_MESSAGE = "계좌번호는 필수 입력 항목입니다.";
    public static final String BANK_ACCOUNT_SIZE_MESSAGE = "계좌번호가 20글자를 초과하였습니다.";

    public static final String RESIDENT_REGISTRATION_NUMBER_PATTERN_REGEXP = "^[0-9]{6}-[1-4][0-9]{6}$";
    public static final String RESIDENT_REGISTRATION_NUMBER_PATTERN_MESSAGE = "잘못된 주민번호 형식입니다.";

    public static final String SALARY_POSITIVE_MESSAGE = "급여가 0보다 작습니다.";

    public static final String EMPLOYMENT_DATE_PATTERN_REGEXP = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String EMPLOYMENT_DATE_PATTERN_MESSAGE = "잘못된 날짜 형식입니다.";

    public static final String BIRTH_DATE_PATTERN_REGEXP = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String BIRTH_DATE_PATTERN_MESSAGE = "잘못된 날짜 형식입니다.";

    public static final String RELATIONSHIP_NOT_NULL_MESSAGE = "가족관계는 필수 입력 항목입니다.";

    public static final String SURVIVAL_LIVE = "생존";
    public static final String SURVIVAL_DEATH = "사망";
}

