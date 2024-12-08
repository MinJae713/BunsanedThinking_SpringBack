package com.example.bunsanedthinking_springback.global.constants.service.employee.managementPlanning;

public class ManagementPlanningConstants {
    public static final String DEPARTMENT_INFORMATION_NOT_FOUND = "해당하는 부서 정보가 존재하지 않습니다.";

    public static final String NAME_LENGTH_MESSAGE = "부서 이름은 최대 10자 이내로 작성해야 합니다.";
    public static final String NAME_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_FORMAT_MESSAGE = "부서 이름은 한글 또는 영문만 입력 가능합니다.";

    public static final String TASK_LENGTH_MESSAGE = "주 업무는 최대 100자 이내로 작성해야 합니다.";
    public static final String TASK_FORMAT_REGEXP = "^[a-zA-Z가-힣,\\s]+$";
    public static final String TASK_FORMAT_MESSAGE = "주 업무는 한글, 영문, 공백, 쉼표(,)만 입력 가능합니다.";

    public static final String PURPOSE_LENGTH_MESSAGE = "부서 목적은 최대 25자 이내로 작성해야 합니다.";
    public static final String PURPOSE_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String PURPOSE_FORMAT_MESSAGE = "부서 목적은 한글 또는 영문만 입력 가능합니다.";

    public static final String HEAD_NAME_LENGTH_MESSAGE = "부서장 이름이 20글자를 초과하였습니다.";
    public static final String HEAD_NAME_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String HEAD_NAME_FORMAT_MESSAGE = "부서장 이름은 한글 또는 영문만 입력 가능합니다.";
}
