package com.example.bunsanedthinking_springback.global.constants.common;

public class CommonConstants {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String STRING_EMPTY = "";

    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "이름은 공백, 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";
    public static final String NAME_SIZE_MESSAGE = "이름이 20글자를 초과하였습니다.";

    public static final String PHONE_NUMBER_PATTERN_REGEXP = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String PHONE_NUMBER_PATTERN_MESSAGE = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx";

    public static final String ID_POSITIVE_MESSAGE = "잘못된 직원 번호입니다.";

    public static final String DEPARTMENT_ID_POSITIVE_MESSAGE = "잘못된 부서 번호입니다.";

}
