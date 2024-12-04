package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.CustomerDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpRequest {
    @Pattern(regexp = CommonConstants.NAME_PATTERN_REGEXP, message = CommonConstants.NAME_PATTERN_MESSAGE)
    private String name;
    @NotBlank(message = CustomerDTOConstants.PHONE_NUMBER_NOT_BLANK_MESSAGE)
    @Pattern(regexp = CommonConstants.PHONE_NUMBER_PATTERN_REGEXP, message = CommonConstants.PHONE_NUMBER_PATTERN_MESSAGE)
    private String phoneNumber;
    private String job;
    @Range(min = 1, max = 120, message = CustomerDTOConstants.AGE_RANGE_MESSAGE)
    private int age;
    private Gender gender;
    @Pattern(regexp = CustomerDTOConstants.RESIDENT_REGISTRATION_NUMBER_REGEXP, message = CustomerDTOConstants.RESIDENT_REGISTRATION_NUMBER_MESSAGE)
    private String residentRegistrationNumber;
    @NotBlank(message = CustomerDTOConstants.ADDRESS_NOT_BLANK_MESSAGE)
    private String address;
    @Positive(message = CustomerDTOConstants.PROPERTY_POSITIVE_MESSAGE)
    private long property;
    @NotBlank(message = CustomerDTOConstants.BANK_NAME_NOT_BLANK_MESSAGE)
    @Pattern(regexp = CustomerDTOConstants.BANK_NAME_PATTERN_REGEXP, message = CustomerDTOConstants.BANK_NAME_PATTERN_MESSAGE)
    private String bankName;
    @NotBlank(message = CustomerDTOConstants.BANK_ACCOUNT_NOT_BLANK_MESSAGE)
    @Pattern(regexp = CustomerDTOConstants.BANK_ACCOUNT_PATTERN_REGEXP, message = CustomerDTOConstants.BANK_ACCOUNT_PATTERN_MESSAGE)
    private String bankAccount;
    private List<SignUpAccidentHistoryRequest> tempAccidentHistoryList;
    private List<SignUpSurgeryHistoryRequest> tempSurgeryHistoryList;
    private List<SignUpDiseaseHistoryRequest> tempDiseaseHistoryList;
}
