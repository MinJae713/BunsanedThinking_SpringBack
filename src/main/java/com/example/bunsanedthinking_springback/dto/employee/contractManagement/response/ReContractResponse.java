package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos.CustomerInfoResponse;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReContractResponse extends AbstractContractResponse {
    private String expirationDate; // 만기 날짜
    private String reContractStatus; // 접수 상태

    public ReContractResponse(int id, CustomerInfoResponse customerInfoResponse, int productId, String expirationDate, String reContractStatus) {
        super(id, customerInfoResponse, productId);
        this.expirationDate = expirationDate;
        this.reContractStatus = reContractStatus;
    }

    public static ReContractResponse of(CustomerInfoResponse customerInfoResponse, Recontract recontract) {
        int id = recontract.getId();
        int productId = recontract.getProductId();
        String expirationDate = recontract.getExpirationDate();
        String reContractStatus = recontract.getRecontractStatus().getText();
        return new ReContractResponse(id, customerInfoResponse, productId, expirationDate, reContractStatus);
    }
}
