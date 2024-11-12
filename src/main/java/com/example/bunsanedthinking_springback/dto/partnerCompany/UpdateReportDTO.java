package com.example.bunsanedthinking_springback.dto.partnerCompany;

import com.example.bunsanedthinking_springback.vo.ReportVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReportDTO {
    private int accident_id;
    private Integer damage_assessment_money;
}
