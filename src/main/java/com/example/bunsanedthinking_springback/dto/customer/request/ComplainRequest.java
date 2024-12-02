package com.example.bunsanedthinking_springback.dto.customer.request;

import com.example.bunsanedthinking_springback.global.constants.service.customer.dto.CustomerDTOConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class ComplainRequest {
    @Range(min = 0, max = 3, message = CustomerDTOConstants.COMPLAIN_TYPE_RANGE)
    private int complainType;
    @NotBlank(message = CustomerDTOConstants.TITLE_NOT_BLANK)
    private String title;
    @NotBlank(message = CustomerDTOConstants.CONTENT_NOT_BLANK)
    private String content;
    private int customerId;
}
