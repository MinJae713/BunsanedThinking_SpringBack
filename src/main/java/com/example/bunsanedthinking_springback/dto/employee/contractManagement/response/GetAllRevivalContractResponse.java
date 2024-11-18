package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRevivalContractResponse {
    private CustomerInfoResponse customerInfoResponse;
    private int productId;
    private String terminationDate;
    private String revivalStatus;
}
