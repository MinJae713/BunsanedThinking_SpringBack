package com.example.bunsanedthinking_springback.dto.mo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDepartmentDTO {
    private int id;
    private String head_name;
    private String name;
    private String purpose;
    private String task;
}
