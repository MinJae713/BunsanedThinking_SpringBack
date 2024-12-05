package com.example.bunsanedthinking_springback.global.constants.service.employee.managementPlanning;

public class ManagementPlanningDTOConstants {
    public static final String HEAD_NAME_NOT_BLANK_MESSAGE = "부서장 이름은 필수 입력 항목입니다.";
    public static final String HEAD_NAME_SIZE_MESSAGE = "부서장 이름이 20글자를 초과하였습니다.";
    public static final String HEAD_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String HEAD_NAME_PATTERN_MESSAGE = "부서장 이름은 한글 또는 영문만 입력 가능합니다.";

    public static final String NAME_NOT_BLANK = "부서 이름은 필수 입력 값입니다.";
    public static final String NAME_SIZE_MESSAGE = "부서 이름은 최대 10자 이내로 작성해야 합니다.";
    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "부서 이름은 한글 또는 영문만 입력 가능합니다.";

    public static final String PURPOSE_NOT_BLANK_MESSAGE = "부서 목적은 필수 입력 값입니다.";
    public static final String PURPOSE_SIZE_MESSAGE = "부서 목적은 최대 25자 이내로 작성해야 합니다.";
    public static final String PURPOSE_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String PURPOSE_PATTERN_MESSAGE = "부서 목적은 한글 또는 영문만 입력 가능합니다.";

    public static final String TASK_NOT_BLANK_MESSAGE = "주 업무는 필수 입력 값입니다.";
    public static final String TASK_SIZE_MESSAGE = "주 업무는 최대 100자 이내로 작성해야 합니다.";
    public static final String TASK_PATTERN_REGEXP = "^[a-zA-Z가-힣,\\s]+$";
    public static final String TASK_PATTERN_MESSAGE = "주 업무는 한글, 영문, 공백, 쉼표(,)만 입력 가능합니다.";
}
