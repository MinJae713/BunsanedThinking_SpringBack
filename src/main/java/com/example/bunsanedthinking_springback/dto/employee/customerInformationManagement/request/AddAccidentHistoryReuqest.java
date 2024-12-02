package com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAccidentHistoryReuqest {
    private String date;
    private String accidentDetail;
}
