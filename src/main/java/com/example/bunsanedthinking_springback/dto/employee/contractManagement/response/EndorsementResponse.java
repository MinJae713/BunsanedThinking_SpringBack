package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos.CustomerInfoResponse;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
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
public class EndorsementResponse extends AbstractContractResponse {
    private String applyDate;
    private String endorsementStatus;

    public EndorsementResponse(int id, CustomerInfoResponse customerInfoResponse,
                               int productId, String applyDate, String endorsementStatus) {
        super(id, customerInfoResponse, productId);
        this.applyDate = applyDate;
        this.endorsementStatus = endorsementStatus;
    }

    public static EndorsementResponse of(CustomerInfoResponse customerInfoResponse, Endorsement endorsement) {
        int id = endorsement.getId();
        int productId = endorsement.getProductId();
        Date applyDate = endorsement.getApplyDate();
        String endorsementStatus = endorsement.getEndorsementStatus().getText();
        return new EndorsementResponse(id, customerInfoResponse, productId,
                DateUtils.toString(applyDate), endorsementStatus);
    }
}
