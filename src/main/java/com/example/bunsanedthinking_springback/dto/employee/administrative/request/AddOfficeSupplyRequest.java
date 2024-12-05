package com.example.bunsanedthinking_springback.dto.employee.administrative.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddOfficeSupplyRequest {
    private String name;
    private String description;
    private int inventory;
    private int total_inventory;
    private int department_id;
}
