package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement.CustomerInformationManagementDTOConstants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSurgeryHistoryRequest {

    @NotNull(message = CustomerInformationManagementDTOConstants.ID_NOT_NULL_MESSAGE)
    @Min(value = 6100, message = CustomerInformationManagementDTOConstants.SURGERY_ID_MIN_MESSAGE)
    private int id;

    @NotBlank(message = CustomerInformationManagementDTOConstants.DATE_NOT_BLANK_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.DATE_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.DATE_PATTERN_MESSAGE)
    private String date;

    @NotBlank(message = CustomerInformationManagementDTOConstants.HOSPITAL_NAME_NOT_BLANK_MESSAGE)
    @Size(max = 20, message = CustomerInformationManagementDTOConstants.HOSPITAL_NAME_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.HOSPITAL_NAME_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.HOSPITAL_NAME_PATTERN_MESSAGE)
    private String hospitalName;

    @NotBlank(message = CustomerInformationManagementDTOConstants.SURGERY_NAME_NOT_BLANK_MESSAGE)
    @Size(max = 20, message = CustomerInformationManagementDTOConstants.SURGERY_NAME_SIZE_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.SURGERY_NAME_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.SURGERY_NAME_PATTERN_MESSAGE)
    private String name;
}
