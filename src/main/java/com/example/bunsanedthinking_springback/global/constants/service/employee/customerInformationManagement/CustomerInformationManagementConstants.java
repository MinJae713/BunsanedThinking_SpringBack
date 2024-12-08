package com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement;

public class CustomerInformationManagementConstants {
    public static final String CUSTOMER_INFORMATION_NULL = "해당하는 고객 정보가 존재하지 않습니다.";

    public static final String INVALID_ACCIDENT_ID = "유효하지 않은 ID로 인해 사고 이력 업데이트가 무시되었습니다: ";
    public static final String ACCIDENT_HISTORY_NOT_FOUND = "ID로 사고 이력을 찾을 수 없습니다: ";
    public static final String NOT_CUSTOMER_ASSIGNED_ACCIDENT_HISTORY = "해당 고객의 사고 이력이 아닙니다: ";
    public static final String UPDATE_ACCIDENT_ERROR = "사고 이력 업데이트 중 오류 발생: ";

    public static final String INVALID_SURGERY_ID = "유효하지 않은 ID로 인해 수술 이력 업데이트가 무시되었습니다: ";
    public static final String SURGERY_HISTORY_NOT_FOUND = "ID로 수술 이력을 찾을 수 없습니다: ";
    public static final String NOT_CUSTOMER_ASSIGNED_SURGERY_HISTORY = "해당 고객의 수술 이력이 아닙니다: ";
    public static final String UPDATE_SURGERY_ERROR = "수술 이력 업데이트 중 오류 발생: ";

    public static final String INVALID_DISEASE_ID = "유효하지 않은 ID로 인해 병력 업데이트가 무시되었습니다: ";
    public static final String DISEASE_HISTORY_NOT_FOUND = "ID로 병력을 찾을 수 없습니다: ";
    public static final String NOT_CUSTOMER_ASSIGNED_DISEASE_HISTORY = "해당 고객의 병력이 아닙니다: ";
    public static final String UPDATE_DISEASE_ERROR = "병력 업데이트 중 오류 발생: ";

    public static final String NAME_LENGTH_MESSAGE = "이름은 최대 20자 이내로 작성해야 합니다.";
    public static final String NAME_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_FORMAT_MESSAGE = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String PHONE_NUMBER_LENGTH_MESSAGE = "핸드폰 번호는 반드시 13자리 형식이어야 합니다. (예: 010-1234-5678)";
    public static final String PHONE_NUMBER_FORMAT_REGEXP = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String PHONE_NUMBER_FORMAT_MESSAGE = "핸드폰 번호의 형식이 올바르지 않습니다. (예: 010-1234-5678)";

    public static final String JOB_LENGTH_MESSAGE = "직업은 최대 20자 이내로 작성해야 합니다.";
    public static final String JOB_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String JOB_FORMAT_MESSAGE = "직업은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String AGE_INVALID_MESSAGE = "나이는 숫자만 입력 가능합니다.";
    public static final String AGE_MIN_VALUE = "나이의 값은 최소 1이어야 합니다.";
    public static final String AGE_MAX_VALUE = "나이의 값은 최대 120이어야 합니다.";

    public static final String ADDRESS_LENGTH_MESSAGE = "주소는 최대 100자 이내로 작성해야 합니다.";

    public static final String PROPERTY_INVALID_MESSAGE = "재산은 숫자만 입력 가능합니다.";
    public static final String PROPERTY_MIN_VALUE = "재산의 값은 최소 1이어야 합니다.";

    public static final String BANK_NAME_LENGTH_MESSAGE = "은행 이름은 최대 10자 이내로 작성해야 합니다.";
    public static final String BANK_NAME_FORMAT_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String BANK_NAME_FORMAT_MESSAGE = "은행 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String BANK_ACCOUNT_LENGTH_MESSAGE = "계좌번호은 최대 20자 이내로 작성해야 합니다.";
    public static final String BANK_ACCOUNT_FORMAT_REGEXP = "^[0-9-]+$";
    public static final String BANK_ACCOUNT_FORMAT_MESSAGE = "계좌번호는 숫자와 '-' 기호만 입력 가능합니다.";
}
