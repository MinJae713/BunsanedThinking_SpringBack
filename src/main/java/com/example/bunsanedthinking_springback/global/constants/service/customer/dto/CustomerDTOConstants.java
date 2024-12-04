package com.example.bunsanedthinking_springback.global.constants.service.customer.dto;

public class CustomerDTOConstants {
    public static final String COMPLAIN_TYPE_RANGE = "4가지 민원 중에서 입력해주세요";
    public static final String TITLE_NOT_BLANK = "민원 제목은 반드시 입력해주세요";
    public static final String CONTENT_NOT_BLANK = "민원 내용은 반드시 입력해주세요";

    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String PHONE_NUMBER_NOT_BLANK_MESSAGE = "전화번호는 반드시 입력해주세요";
    public static final String PHONE_NUMBER_PATTERN_REGEXP = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String PHONE_NUMBER_PATTERN_MESSAGE = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx";

    public static final String AGE_RANGE_MESSAGE = "나이는 1~120 이내에서 입력해주세요";

    public static final String RESIDENT_REGISTRATION_NUMBER_REGEXP = "^\\d{2}[0-1]\\d[0-3]\\d-?[1-6]\\d{6}$";
    public static final String RESIDENT_REGISTRATION_NUMBER_MESSAGE = "주민등록번호 형식과 일치하지 않습니다";

    public static final String ADDRESS_NOT_BLANK_MESSAGE = "주소를 반드시 입력해주세요";

    public static final String PROPERTY_POSITIVE_MESSAGE = "재산은 양수여야 합니다";

    public static final String BANK_NAME_NOT_BLANK_MESSAGE = "은행 이름을 반드시 입력해주세요";
    public static final String BANK_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String BANK_NAME_PATTERN_MESSAGE = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String BANK_ACCOUNT_NOT_BLANK_MESSAGE = "계좌번호를 반드시 입력해주세요";
    public static final String BANK_ACCOUNT_PATTERN_REGEXP = "^[\\d-]+$";
    public static final String BANK_ACCOUNT_PATTERN_MESSAGE = "유효하지 않은 계좌번호입니다";

    public static final String CONTRACT_ID_MESSAGE = "금액은 양수여야 합니다";
    public static final String MONEY_MESSAGE = "납입 경로를 다시 지정해주세요";

    public static final String LOCATION_NOT_BLANK_MESSAGE = "사고 위치는 반드시 입력해주세요";
    public static final String SERVICE_TYPE_RANGE_MESSAGE = "6가지 유형중에 선택해주세요";

}
