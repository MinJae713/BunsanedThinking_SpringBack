package com.example.bunsanedthinking_springback.dto.employee.administrative.request;

import com.example.bunsanedthinking_springback.global.constants.service.employee.administrative.AdministrativeDTOConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateOfficeSupplyRequest {
    private int id;
    private int index;
    @NotBlank(message = AdministrativeDTOConstants.INPUT_NOT_BLANK_MESSAGE)
    private String input;
}
