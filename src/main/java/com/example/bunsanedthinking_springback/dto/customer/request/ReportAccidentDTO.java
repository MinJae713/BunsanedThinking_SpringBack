package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ReportAccidentDTO {
    private Date accidentDate;
    private String location;
    private int serviceType;
    private int customerId;
}
