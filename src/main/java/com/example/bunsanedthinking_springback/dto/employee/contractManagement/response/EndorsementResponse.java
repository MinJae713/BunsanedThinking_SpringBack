package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date applyDate; // 신청 날짜
    private String endorsementStatus; // 심사 상태

    public EndorsementResponse(int id, CustomerInfoResponse customerInfoResponse, int productId, Date applyDate, String endorsementStatus) {
        super(id, customerInfoResponse, productId);
        this.applyDate = applyDate;
        this.endorsementStatus = endorsementStatus;
    }

    public static EndorsementResponse of(CustomerInfoResponse customerInfoResponse, Endorsement endorsement) {
        int id = endorsement.getId();
        int productId = endorsement.getProductId();
        Date applyDate = endorsement.getApplyDate();
        String endorsementStatus = endorsement.getEndorsementStatus().getText();
        return new EndorsementResponse(id, customerInfoResponse, productId, applyDate, endorsementStatus);
    }
}
