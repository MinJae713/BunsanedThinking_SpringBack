package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTerminatingContractResponse {
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date applyDate;
    private String terminationStatus;

    public static GetAllTerminatingContractResponse of(CustomerInfoResponse customerInfoResponse, Termination termination) {
        int productId = termination.getProductId();
        Date applyDate = termination.getApplyDate();
        String terminationStatus = termination.getTerminationStatus().getText();
        return new GetAllTerminatingContractResponse(customerInfoResponse, productId, applyDate, terminationStatus);
    }
}
