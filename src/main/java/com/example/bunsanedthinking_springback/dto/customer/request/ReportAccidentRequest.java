package com.example.bunsanedthinking_springback.dto.customer.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.CustomerDTOConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ReportAccidentRequest {
    private Date accidentDate;
    @NotBlank(message = CustomerDTOConstants.LOCATION_NOT_BLANK_MESSAGE)
    private String location;
    @Range(min = 0, max = 5, message = CustomerDTOConstants.SERVICE_TYPE_RANGE_MESSAGE)
    private int serviceType;
    private int customerId;
}
