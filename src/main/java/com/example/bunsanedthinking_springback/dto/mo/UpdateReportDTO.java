package com.example.bunsanedthinking_springback.dto.mo;

import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReportDTO {
    private ReportVO report;

}
