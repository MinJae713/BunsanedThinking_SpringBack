package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement.CustomerInformationManagementDTOConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddCustomerInformationRequest {

    @NotBlank(message = CustomerInformationManagementDTOConstants.NAME_NOT_BLANK_MESSAGE)
    @Size(max = 20, message = CustomerInformationManagementDTOConstants.NAME_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.NAME_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.NAME_PATTERN_MESSAGE)
    private String name;

    @NotBlank(message = CustomerInformationManagementDTOConstants.PHONE_NUMBER_NOT_BLANK_MESSAGE)
    @Size(min = 13, max = 13, message = CustomerInformationManagementDTOConstants.PHONE_NUMBER_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.PHONE_NUMBER_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.PHONE_NUMBER_PATTERN_MESSAGE)
    private String phoneNumber;

    @NotBlank(message = CustomerInformationManagementDTOConstants.JOB_NOT_BLANK_MESSAGE)
    @Size(max = 20, message = CustomerInformationManagementDTOConstants.JOB_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.JOB_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.JOB_PATTERN_MESSAGE)
    private String job;

    @NotNull(message = CustomerInformationManagementDTOConstants.AGE_NOT_NULL_MESSAGE)
    @Min(value = 1, message = CustomerInformationManagementDTOConstants.AGE_MIN_MESSAGE)
    @Max(value = 120, message = CustomerInformationManagementDTOConstants.AGE_MAX_MESSAGE)
    private Integer age;

    @NotNull(message = CustomerInformationManagementDTOConstants.GENDER_NOT_NULL_MESSAGE)
    private Integer gender;

    @NotBlank(message = CustomerInformationManagementDTOConstants.RESIDENT_REGISTRATION_NUMBER_NOT_BLANK_MESSAGE)
    @Size(min = 14, max = 14, message = CustomerInformationManagementDTOConstants.RESIDENT_REGISTRATION_NUMBER_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.RESIDENT_REGISTRATION_NUMBER_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.RESIDENT_REGISTRATION_NUMBER_PATTERN_MESSAGE)
    private String residentRegistrationNumber;

    @NotBlank(message = CustomerInformationManagementDTOConstants.ADDRESS_NOT_BLANK_MESSAGE)
    @Size(max = 100, message = CustomerInformationManagementDTOConstants.ADDRESS_SIZE_MESSAGE)
    private String address;

    @NotNull(message = CustomerInformationManagementDTOConstants.PROPERTY_NOT_NULL_MESSAGE)
    @Min(value = 1, message = CustomerInformationManagementDTOConstants.PROPERTY_MIN_MESSAGE)
    private Long property;

    @Valid
    private List<AddAccidentHistoryRequest> accidentHistoryList;

    @Valid
    private List<AddSurgeryHistoryRequest> surgeryHistoryList;

    @Valid
    private List<AddDiseaseHistoryRequest> diseaseHistoryList;

    @NotBlank(message = CustomerInformationManagementDTOConstants.BANK_NAME_NOT_BLANK_MESSAGE)
    @Size(max = 10, message = CustomerInformationManagementDTOConstants.BANK_NAME_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.BANK_NAME_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.BANK_NAME_PATTERN_MESSAGE)
    private String bankName;

    @NotBlank(message = CustomerInformationManagementDTOConstants.BANK_ACCOUNT_NOT_BLANK_MESSAGE)
    @Size(max = 20, message = CustomerInformationManagementDTOConstants.BANK_ACCOUNT_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.BANK_ACCOUNT_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.BANK_ACCOUNT_PATTERN_MESSAGE)
    private String bankAccount;
}
