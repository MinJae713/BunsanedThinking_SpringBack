package com.example.bunsanedthinking_springback.dto.employee.administrative.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateOfficeSupplyRequest {
    private int id;
    private int index;
    private String input;
}