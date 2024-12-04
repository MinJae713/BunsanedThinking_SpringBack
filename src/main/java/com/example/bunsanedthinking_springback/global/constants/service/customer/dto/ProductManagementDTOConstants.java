package com.example.bunsanedthinking_springback.global.constants.service.customer.dto;

public class ProductManagementDTOConstants {
    public static final String NAME_NOT_BLANK_MESSAGE = "보험 이름은 필수 값입니다.";
    public static final String NAME_SIZE_MESSAGE = "보험 이름은 최대 20자까지 허용됩니다.";
    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "보험 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String MAXIMUM_MONEY_NOT_NULL_MESSAGE = "한도는 필수 값입니다.";
    public static final String MAXIMUM_MONEY_MIN_MESSAGE = "한도는 최소 1이어야 합니다.";

    public static final String INSURANCE_TYPE_NOT_NULL_MESSAGE = "보험 유형은 필수 값입니다.";

    public static final String AGE_RANGE_NOT_NULL_MESSAGE = "나이 범위는 필수 값입니다.";
    public static final String AGE_RANGE_MIN_MESSAGE = "나이의 값은 최소 1이어야 합니다.";
    public static final String AGE_RANGE_MAX_MESSAGE = "나이의 값은 최대 120이어야 합니다.";

    public static final String MONTHLY_PREMIUM_NOT_NULL_MESSAGE = "월 보험료는 필수 값입니다.";
    public static final String MONTHLY_PREMIUM_MIN_MESSAGE = "월 보험료는 최소 1이어야 합니다.";

    public static final String CONTRACT_PERIOD_NOT_NULL_MESSAGE = "계약 기간은 필수 값입니다.";
    public static final String CONTRACT_PERIOD_MIN_MESSAGE = "계약 기간은 최소 1이어야 합니다.";

    public static final String COVERAGE_NOT_BLANK_MESSAGE = "보장 내용은 필수 값입니다.";
    public static final String COVERAGE_SIZE_MESSAGE = "보장 내용은 최대 120자까지 허용됩니다.";

    public static final String VEHICLE_TYPE_NOT_NULL_MESSAGE = "차량 유형은 필수 값입니다.";

    public static final String ACCIDENT_LIMIT_NOT_NULL_MESSAGE = "사고 한도는 필수 값입니다.";
    public static final String ACCIDENT_LIMIT_MIN_MESSAGE = "사고 한도는 최소 1이어야 합니다.";

    public static final String DISEASE_NAME_NOT_BLANK_MESSAGE = "질병 이름은 필수 값입니다.";
    public static final String DISEASE_NAME_SIZE_MESSAGE = "질병 이름은 최대 20자까지 허용됩니다.";
    public static final String DISEASE_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String DISEASE_NAME_PATTERN_MESSAGE = "질병 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String DISEASE_LIMIT_NOT_NULL = "질병 한도는 필수 값입니다.";
    public static final String DISEASE_LIMIT_MIN_MESSAGE = "질병 한도는 최소 1이어야 합니다.";

    public static final String SURGERIES_LIMIT_NOT_NULL = "수술 한도는 필수 값입니다.";
    public static final String SURGERIES_LIMIT_MIN_MESSAGE = "수술 한도는 최소 1이어야 합니다.";

    public static final String INJURY_TYPE_NOT_NULL = "상해 유형은 필수 값입니다.";

    public static final String ID_NOT_NULL = "ID는 필수 값입니다.";




}
