package com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.managementPlanning.ManagementPlanningDTOConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDepartmentRequest {

    @NotBlank(message = ManagementPlanningDTOConstants.HEAD_NAME_NOT_BLANK_MESSAGE)
    @Size(max=20, message = ManagementPlanningDTOConstants.HEAD_NAME_SIZE_MESSAGE)
    @Pattern(regexp = ManagementPlanningDTOConstants.HEAD_NAME_PATTERN_REGEXP,
            message = ManagementPlanningDTOConstants.HEAD_NAME_PATTERN_MESSAGE)
    private String head_name;

    @NotBlank(message = ManagementPlanningDTOConstants.NAME_NOT_BLANK)
    @Size(max = 10, message = ManagementPlanningDTOConstants.NAME_SIZE_MESSAGE)
    @Pattern(regexp = ManagementPlanningDTOConstants.NAME_PATTERN_REGEXP,
            message = ManagementPlanningDTOConstants.NAME_PATTERN_MESSAGE)
    private String name;

    @NotBlank(message = ManagementPlanningDTOConstants.PURPOSE_NOT_BLANK_MESSAGE)
    @Size(max = 30, message = ManagementPlanningDTOConstants.PURPOSE_SIZE_MESSAGE)
    @Pattern(regexp = ManagementPlanningDTOConstants.PURPOSE_PATTERN_REGEXP,
            message = ManagementPlanningDTOConstants.PURPOSE_PATTERN_MESSAGE)
    private String purpose;

    @NotBlank(message = ManagementPlanningDTOConstants.TASK_NOT_BLANK_MESSAGE)
    @Size(max = 100, message = ManagementPlanningDTOConstants.TASK_SIZE_MESSAGE)
    @Pattern(regexp = ManagementPlanningDTOConstants.TASK_PATTERN_REGEXP,
            message = ManagementPlanningDTOConstants.TASK_PATTERN_MESSAGE)
    private String task;
}
