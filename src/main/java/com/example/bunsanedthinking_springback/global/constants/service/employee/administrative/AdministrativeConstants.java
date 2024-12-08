package com.example.bunsanedthinking_springback.global.constants.service.employee.administrative;

public class AdministrativeConstants {
    public static final String OFFICE_SUPPLY_NULL = "해당하는 집기 비품 정보가 존재하지 않습니다.";

    public static final String NAME_LENGTH_MESSAGE = "비품 이름이 10글자를 초과하였습니다.";
    public static final String NAME_FORMAT_REGEXP = "^[a-zA-Z가-힣0-9\\s]+$";
    public static final String NAME_FORMAT_MESSAGE = "비품 이름은 한글, 영문, 숫자, 공백만 입력 가능합니다.";

    public static final String DESCRIPTION_LENGTH_MESSAGE = "설명이 20글자를 초과하였습니다.";
    public static final String DESCRIPTION_FORMAT_REGEXP = "^[가-힣a-zA-Z0-9.,!?\\s]+$";
    public static final String DESCRIPTION_FORMAT_MESSAGE = "설명에는 한글, 영문, 숫자, 공백, 일부 특수문자(.,!?)만 입력 가능합니다.";

    public static final String INVENTORY_FORMAT_REGEXP = "^\\d+$";
    public static final String INVENTORY_FORMAT_MESSAGE = "재고는 숫자만 입력 가능합니다.";
    public static final String INVENTORY_MIN_VALUE_MESSAGE = "현재 재고는 최소 1이어야 합니다.";
    public static final String INVENTORY_EXCEEDS_TOTAL_MESSAGE = "현재 재고는 총 재고를 초과할 수 없습니다.";
}
