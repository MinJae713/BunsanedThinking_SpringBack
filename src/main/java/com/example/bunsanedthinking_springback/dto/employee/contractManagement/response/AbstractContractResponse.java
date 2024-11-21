package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractContractResponse {
    protected int id;
    protected CustomerInfoResponse customerInfoResponse;
    protected int productId; // 보험 상품 번호
}
