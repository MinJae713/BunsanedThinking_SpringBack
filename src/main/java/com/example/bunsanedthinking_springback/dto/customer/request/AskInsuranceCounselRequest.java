package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AskInsuranceCounselRequest {
    private int customerId;
    private int insuranceId;
    private Date counselDate;
}
