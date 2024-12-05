package com.example.bunsanedthinking_springback.dto.employee.administrative.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.administrative.AdministrativeDTOConstants;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddOfficeSupplyRequest {

    @NotBlank(message = AdministrativeDTOConstants.NAME_NOT_BLANK_MESSAGE)
    @Size(max=10, message = AdministrativeDTOConstants.NAME_SIZE_MESSAGE)
    @Pattern(regexp = AdministrativeDTOConstants.NAME_PATTERN_REGEXP,
            message = AdministrativeDTOConstants.NAME_PATTERN_MESSAGE)
    private String name;

    @NotBlank(message = AdministrativeDTOConstants.DESCRIPTION_NOT_BLANK_MESSAGE)
    @Size(max=20, message = AdministrativeDTOConstants.DESCRIPTION_SIZE_MESSAGE)
    @Pattern(regexp = AdministrativeDTOConstants.DESCRIPTION_PATTERN_REGEXP,
            message = AdministrativeDTOConstants.DESCRIPTION_PATTERN_MESSAGE)
    private String description;

    @NotNull(message = AdministrativeDTOConstants.INVENTORY_NOT_NULL_MESSAGE)
    @Positive(message = AdministrativeDTOConstants.INVENTORY_POSITIVE_MESSAGE)
    private Integer inventory;

    @NotNull(message = AdministrativeDTOConstants.TOTAL_INVENTORY_NOT_NULL_MESSAGE)
    @Positive(message = AdministrativeDTOConstants.TOTAL_INVENTORY_POSITIVE_MESSAGE)
    private Integer total_inventory;

    @NotNull(message = AdministrativeDTOConstants.DEPARTMENT_ID_NOT_NULL_MESSAGE)
    @Min(value = 9100,
            message = AdministrativeDTOConstants.DEPARTMENT_ID_MIN_MESSAGE)
    private Integer department_id;
}
