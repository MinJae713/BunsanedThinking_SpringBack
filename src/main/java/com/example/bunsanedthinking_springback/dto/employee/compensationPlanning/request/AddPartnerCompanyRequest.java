package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.service.employee.compensationPlanning.CompensationPlanningDTOConstants;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPartnerCompanyRequest {
    @Pattern(regexp = CommonConstants.NAME_PATTERN_REGEXP, message = CommonConstants.NAME_PATTERN_MESSAGE)
    private String name;
    @Pattern(regexp = CommonConstants.PHONE_NUMBER_PATTERN_REGEXP, message = CommonConstants.PHONE_NUMBER_PATTERN_MESSAGE)
    private String phoneNumber;
    private int partnerCompanyType;
    @Pattern(regexp = CompensationPlanningDTOConstants.HEAD_NAME_PATTERN_REGEXP, message = CompensationPlanningDTOConstants.HEAD_NAME_PATTERN_MESSAGE)
    private String headName;
    @Pattern(regexp = CompensationPlanningDTOConstants.HEAD_PHONE_NUMBER_PATTERN_REGEXP, message = CompensationPlanningDTOConstants.HEAD_PHONE_NUMBER_PATTERN_MESSAGE)
    private String headPhoneNumber;
}
