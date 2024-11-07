package com.example.bunsanedthinking_springback.dto.mo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustomerInformationDTO {
    private int index;
    private String input;
    private int id; // 고객 ID
}
