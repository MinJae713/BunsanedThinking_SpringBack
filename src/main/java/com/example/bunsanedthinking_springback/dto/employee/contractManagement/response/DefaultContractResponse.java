package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos.CustomerInfoResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultContractResponse extends AbstractContractResponse {
    private String lastPaidDate; // 납부 날짜

    public DefaultContractResponse(int id, CustomerInfoResponse customerInfoResponse, int productId, String lastPaidDate) {
        super(id, customerInfoResponse, productId);
        this.lastPaidDate = lastPaidDate;
    }

    public static DefaultContractResponse of(CustomerInfoResponse customerInfoResponse, Contract contract) {
        int id = contract.getId();
        int productId = contract.getProductId();
        String lastPaidDate = contract.getLastPaidDate();
        return new DefaultContractResponse(id, customerInfoResponse, productId, lastPaidDate);
    }
}
