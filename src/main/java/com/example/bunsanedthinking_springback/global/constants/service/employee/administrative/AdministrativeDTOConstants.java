package com.example.bunsanedthinking_springback.global.constants.service.employee.administrative;

public class AdministrativeDTOConstants {
    public static final String NAME_NOT_BLANK_MESSAGE = "비품 이름은 필수 입력 항목입니다.";
    public static final String NAME_SIZE_MESSAGE = "비품 이름이 10글자를 초과하였습니다.";
    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "비품 이름은 한글 또는 영문만 입력 가능합니다.";

    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "설명은 필수 입력 항목입니다.";
    public static final String DESCRIPTION_SIZE_MESSAGE = "설명이 20글자를 초과하였습니다.";
    public static final String DESCRIPTION_PATTERN_REGEXP = "^[가-힣a-zA-Z0-9.,!?\\s]+$";
    public static final String DESCRIPTION_PATTERN_MESSAGE = "설명에는 한글, 영문, 숫자, 공백, 일부 특수문자(.,!?)만 입력 가능합니다.";

    public static final String INVENTORY_NOT_NULL_MESSAGE = "현재 재고는 필수 입력 항목입니다.";
    public static final String INVENTORY_POSITIVE_MESSAGE = "현재 재고는 최소 1이어야 합니다.";

    public static final String TOTAL_INVENTORY_NOT_NULL_MESSAGE = "총 재고는 필수 입력 항목입니다.";
    public static final String TOTAL_INVENTORY_POSITIVE_MESSAGE = "현재 재고는 최소 1이어야 합니다.";

    public static final String DEPARTMENT_ID_NOT_NULL_MESSAGE = "부서 ID는 필수 입력 항목입니다.";
    public static final String DEPARTMENT_ID_MIN_MESSAGE = "부서 ID는 9100 이상의 숫자여야 합니다.";

    public static final String INPUT_NOT_BLANK_MESSAGE = "수정할 값은 반드시 입력해주세요.";
}
