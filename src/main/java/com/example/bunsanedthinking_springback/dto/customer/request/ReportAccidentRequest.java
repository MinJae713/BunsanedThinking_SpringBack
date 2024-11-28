package com.example.bunsanedthinking_springback.dto.customer.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ReportAccidentRequest {
    private Date accidentDate;
    @NotBlank(message = "사고 위치는 반드시 입력해주세요")
    private String location;
    @Range(min = 0, max = 5, message = "6가지 유형중에 선택해주세요")
    private int serviceType;
    private int customerId;
}
