package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos.CustomerInfoResponse;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminationResponse extends AbstractContractResponse {
    private String applyDate;
    private int terminationFee;
    private String terminationStatus;

    public TerminationResponse(int id, CustomerInfoResponse customerInfoResponse,
                               int productId, String applyDate, int terminationFee, String terminationStatus) {
        super(id, customerInfoResponse, productId);
        this.applyDate = applyDate;
        this.terminationFee = terminationFee;
        this.terminationStatus = terminationStatus;
    }

    public static TerminationResponse of(CustomerInfoResponse customerInfoResponse, Termination termination) {
        int id = termination.getId();
        int productId = termination.getProductId();
        Date applyDate = termination.getApplyDate();
        String terminationStatus = termination.getTerminationStatus().getText();
        int terminationFee = termination.getTerminationFee();
        return new TerminationResponse(id, customerInfoResponse, productId,
                DateUtils.toString(applyDate), terminationFee, terminationStatus);
    }
}
