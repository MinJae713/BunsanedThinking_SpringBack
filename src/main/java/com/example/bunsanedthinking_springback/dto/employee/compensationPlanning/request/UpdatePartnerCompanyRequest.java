package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePartnerCompanyRequest {
    private int index;
    private String input;
    private int partnerCompanyId;
}
