package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultContractResponse {
    private int id;
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    private String lastPaidDate;

    public static DefaultContractResponse of(CustomerInfoResponse customerInfoResponse, Contract contract) {
        int id = contract.getId();
        int productId = contract.getProductId();
        String lastPaidDate = contract.getLastPaidDate();
        return new DefaultContractResponse(id, customerInfoResponse, productId, lastPaidDate);
    }
}
