package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement.CustomerInformationManagementDTOConstants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateAccidentHistoryRequest {

    @NotNull(message = CustomerInformationManagementDTOConstants.ID_NOT_NULL_MESSAGE)
    @Min(value = 4100, message = CustomerInformationManagementDTOConstants.ACCIDENT_ID_MIN_MESSAGE)
    private Integer id; // 기존 데이터 식별용

    @NotBlank(message = CustomerInformationManagementDTOConstants.DATE_NOT_BLANK_MESSAGE)
    @Pattern(regexp = CustomerInformationManagementDTOConstants.DATE_PATTERN_REGEXP,
            message = CustomerInformationManagementDTOConstants.DATE_PATTERN_MESSAGE)
    private String date;

    @NotBlank(message = CustomerInformationManagementDTOConstants.ACCIDENT_DETAIL_NOT_BLANK_MESSAGE)
    @Size(max = 120,
            message = CustomerInformationManagementDTOConstants.ACCIDENT_DETAIL_SIZE_MESSAGE)
    private String accidentDetail;
}