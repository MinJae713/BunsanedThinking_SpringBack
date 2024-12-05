package com.example.bunsanedthinking_springback.dto.partnerCompany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReportRequest {
    private int accident_id;
    private Integer damage_assessment_money;
}
