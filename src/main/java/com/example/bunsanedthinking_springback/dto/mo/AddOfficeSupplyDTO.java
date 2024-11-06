package com.example.bunsanedthinking_springback.dto.mo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddOfficeSupplyDTO {
    private String name;
    private String description;
    private int inventory;
    private int total_inventory;
    private int department_id;
}
