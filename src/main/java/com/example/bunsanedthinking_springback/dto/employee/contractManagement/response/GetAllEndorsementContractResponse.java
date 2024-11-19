package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEndorsementContractResponse {
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date applyDate;
    private String endorsementStatus;

    public static GetAllEndorsementContractResponse of(CustomerInfoResponse customerInfoResponse, Endorsement endorsement) {
        int productId = endorsement.getProductId();
        Date applyDate = endorsement.getApplyDate();
        String endorsementStatus = endorsement.getEndorsementStatus().getText();
        return new GetAllEndorsementContractResponse(customerInfoResponse, productId, applyDate, endorsementStatus);
    }
}
