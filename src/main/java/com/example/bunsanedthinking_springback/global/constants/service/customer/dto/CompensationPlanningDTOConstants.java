package com.example.bunsanedthinking_springback.global.constants.service.customer.dto;

public class CompensationPlanningDTOConstants {
    public static final String HEAD_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String HEAD_NAME_PATTERN_MESSAGE = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String HEAD_PHONE_NUMBER_PATTERN_REGEXP = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String HEAD_PHONE_NUMBER_PATTERN_MESSAGE = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx";

    public static final String INDEX_RANGE_MESSAGE = "유효한 선택 옵션이 아닙니다";
    public static final String INPUT_NOT_BLANK_MESSAGE = "수정 값은 반드시 입력해주세요";
    public static final String PARTNER_COMPANY_ID_MIN_MESSAGE = "유효한 아이디가 아닙니다";
}
