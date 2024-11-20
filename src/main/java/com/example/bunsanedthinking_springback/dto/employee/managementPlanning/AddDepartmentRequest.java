package com.example.bunsanedthinking_springback.dto.employee.managementPlanning;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDepartmentRequest {
    private String head_name;
    private String name;
    private String purpose;
    private String task;
}
