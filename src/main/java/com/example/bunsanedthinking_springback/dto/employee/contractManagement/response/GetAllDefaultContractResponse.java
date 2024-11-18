package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDefaultContractResponse {
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    private String lastPaidDate;

    public static GetAllDefaultContractResponse of(CustomerInfoResponse customerInfoResponse, Contract contract) {
        int productId = contract.getProductId();
        String lastPaidDate = contract.getLastPaidDate();
        return new GetAllDefaultContractResponse(customerInfoResponse, productId, lastPaidDate);
    }
}
