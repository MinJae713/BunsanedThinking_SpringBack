package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReContractResponse {
    private int id;
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    private String expirationDate;
    private String reContractStatus;

    public static ReContractResponse of(CustomerInfoResponse customerInfoResponse, Recontract recontract) {
        int id = recontract.getId();
        int productId = recontract.getProductId();
        String expirationDate = recontract.getExpirationDate();
        String reContractStatus = recontract.getRecontractStatus().getText();
        return new ReContractResponse(id, customerInfoResponse, productId, expirationDate, reContractStatus);
    }
}
