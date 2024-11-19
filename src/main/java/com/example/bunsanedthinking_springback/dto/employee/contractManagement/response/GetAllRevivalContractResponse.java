package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.revival.Revival;
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

    public static GetAllRevivalContractResponse of(CustomerInfoResponse customerInfoResponse, Revival revival) {
        int productId = revival.getProductId();
        String terminationDate = revival.getTerminationDate();
        String revivalStatus = revival.getRevivalStatus().getText();
        return new GetAllRevivalContractResponse(customerInfoResponse, productId,
                terminationDate == null ? "" : terminationDate, revivalStatus);
    }
}
