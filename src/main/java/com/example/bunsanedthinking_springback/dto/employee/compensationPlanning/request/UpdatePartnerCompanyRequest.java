package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.compensationPlanning.CompensationPlanningDTOConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class UpdatePartnerCompanyRequest {
    @Range(min = 1, max = 4, message = CompensationPlanningDTOConstants.INDEX_RANGE_MESSAGE)
    private int index;
    @NotBlank(message = CompensationPlanningDTOConstants.INPUT_NOT_BLANK_MESSAGE)
    private String input;
    @Min(value = 3000, message = CompensationPlanningDTOConstants.PARTNER_COMPANY_ID_MIN_MESSAGE)
    private int partnerCompanyId;
}
