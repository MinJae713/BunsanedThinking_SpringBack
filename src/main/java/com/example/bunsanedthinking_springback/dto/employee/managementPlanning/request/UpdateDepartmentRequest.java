package com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.managementPlanning.ManagementPlanningDTOConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDepartmentRequest {
    private int index;
    private int id;
    @NotBlank(message = ManagementPlanningDTOConstants.INPUT_NOT_BLANK_MESSAGE)
    private String input;
}
