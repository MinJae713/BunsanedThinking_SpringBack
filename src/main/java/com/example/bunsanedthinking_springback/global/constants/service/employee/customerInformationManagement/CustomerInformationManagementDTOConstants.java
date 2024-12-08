package com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement;

public class CustomerInformationManagementDTOConstants {
    public static final String NAME_NOT_BLANK_MESSAGE = "이름은 필수 입력 값입니다.";
    public static final String NAME_SIZE_MESSAGE = "이름은 최대 20자 이내로 작성해야 합니다.";
    public static final String NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String NAME_PATTERN_MESSAGE = "이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String PHONE_NUMBER_NOT_BLANK_MESSAGE = "핸드폰 번호는 필수 입력 값입니다.";
    public static final String PHONE_NUMBER_SIZE_MESSAGE = "핸드폰 번호는 반드시 13자리 형식이어야 합니다. (예: 010-1234-5678)";
    public static final String PHONE_NUMBER_PATTERN_REGEXP = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String PHONE_NUMBER_PATTERN_MESSAGE = "핸드폰 번호의 형식이 올바르지 않습니다. (예: 010-1234-5678)";

    public static final String JOB_NOT_BLANK_MESSAGE = "직업은 필수 입력 값입니다.";
    public static final String JOB_SIZE_MESSAGE = "직업은 최대 20자 이내로 작성해야 합니다.";
    public static final String JOB_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String JOB_PATTERN_MESSAGE = "직업은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String AGE_NOT_NULL_MESSAGE = "나이는 필수 입력 값입니다.";
    public static final String AGE_MIN_MESSAGE = "나이의 값은 최소 1이어야 합니다.";
    public static final String AGE_MAX_MESSAGE = "나이의 값은 최대 120이어야 합니다.";

    public static final String GENDER_NOT_NULL_MESSAGE = "성별은 필수 입력 값입니다.";

    public static final String RESIDENT_REGISTRATION_NUMBER_NOT_BLANK_MESSAGE = "주민등록번호는 필수 입력 값입니다.";
    public static final String RESIDENT_REGISTRATION_NUMBER_SIZE_MESSAGE = "주민등록번호는 반드시 14자리 형식이어야 합니다. (예: 123456-1234567)";
    public static final String RESIDENT_REGISTRATION_NUMBER_PATTERN_REGEXP = "^[0-9]{6}-[1-4][0-9]{6}$";
    public static final String RESIDENT_REGISTRATION_NUMBER_PATTERN_MESSAGE = "주민등록번호 형식이 올바르지 않습니다. (예: 123456-1234567)";

    public static final String ADDRESS_NOT_BLANK_MESSAGE = "주소는 필수 입력 값입니다.";
    public static final String ADDRESS_SIZE_MESSAGE = "주소는 최대 100자 이내로 작성해야 합니다.";

    public static final String PROPERTY_NOT_NULL_MESSAGE = "재산은 필수 입력 값입니다.";
    public static final String PROPERTY_MIN_MESSAGE = "재산의 값은 최소 1이어야 합니다.";

    public static final String BANK_NAME_NOT_BLANK_MESSAGE = "은행 이름은 필수 입력 값입니다.";
    public static final String BANK_NAME_SIZE_MESSAGE = "은행 이름은 최대 10자 이내로 작성해야 합니다.";
    public static final String BANK_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String BANK_NAME_PATTERN_MESSAGE = "은행 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String BANK_ACCOUNT_NOT_BLANK_MESSAGE = "계좌번호는 필수 입력 값입니다.";
    public static final String BANK_ACCOUNT_SIZE_MESSAGE = "계좌번호는 최대 20자 이내로 작성해야 합니다.";
    public static final String BANK_ACCOUNT_PATTERN_REGEXP = "^[0-9-]+$";
    public static final String BANK_ACCOUNT_PATTERN_MESSAGE = "계좌번호는 숫자와 '-' 기호만 입력 가능합니다.";

    public static final String DATE_NOT_BLANK_MESSAGE = "날짜는 필수 입력 값입니다.";
    public static final String DATE_PATTERN_REGEXP = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String DATE_PATTERN_MESSAGE = "날짜 형식은 yyyy-MM-dd 형식이어야 합니다.";

    public static final String ACCIDENT_DETAIL_NOT_BLANK_MESSAGE = "사고 내용은 필수 값입니다.";
    public static final String ACCIDENT_DETAIL_SIZE_MESSAGE = "사고 내용은 최대 120자까지 허용됩니다.";

    public static final String HOSPITAL_NAME_NOT_BLANK_MESSAGE = "병원 이름은 필수 입력 값입니다.";
    public static final String HOSPITAL_NAME_SIZE_MESSAGE = "병원 이름은 최대 20자 이내로 작성해야 합니다.";
    public static final String HOSPITAL_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String HOSPITAL_NAME_PATTERN_MESSAGE = "병원 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String SURGERY_NAME_NOT_BLANK_MESSAGE = "수술 이름은 필수 입력 값입니다.";
    public static final String SURGERY_NAME_SIZE_MESSAGE = "수술 이름은 최대 20자 이내로 작성해야 합니다.";
    public static final String SURGERY_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String SURGERY_NAME_PATTERN_MESSAGE = "수술 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String DISEASE_NAME_NOT_BLANK_MESSAGE = "질병 이름은 필수 입력 값입니다.";
    public static final String DISEASE_NAME_SIZE_MESSAGE = "질병 이름은 최대 20자 이내로 작성해야 합니다.";
    public static final String DISEASE_NAME_PATTERN_REGEXP = "^[a-zA-Z가-힣]+$";
    public static final String DISEASE_NAME_PATTERN_MESSAGE = "질병 이름은 숫자나 특수문자를 포함할 수 없으며, 한글 또는 영문만 허용됩니다.";

    public static final String ID_NOT_NULL_MESSAGE = "이력 ID는 필수 입력 항목입니다.";
    public static final String ACCIDENT_ID_MIN_MESSAGE = "이력 ID는 4100 이상의 숫자여야 합니다.";
    public static final String DISEASE_ID_MIN_MESSAGE = "이력 ID는 5100 이상의 숫자여야 합니다.";
    public static final String SURGERY_ID_MIN_MESSAGE = "이력 ID는 6100 이상의 숫자여야 합니다.";

    public static final String INPUT_NOT_BLANK_MESSAGE = "수정할 값은 반드시 입력해주세요.";
}
