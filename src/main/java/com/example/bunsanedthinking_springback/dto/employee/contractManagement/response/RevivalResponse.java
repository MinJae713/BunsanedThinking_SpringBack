package com.example.bunsanedthinking_springback.dto.employee.contractManagement.response;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.customerInfos.CustomerInfoResponse;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevivalResponse extends AbstractContractResponse {
    private String terminationDate; // 정지 날짜
    private String revivalStatus; // 심사 상태

    public RevivalResponse(int id, CustomerInfoResponse customerInfoResponse, int productId, String terminationDate, String revivalStatus) {
        super(id, customerInfoResponse, productId);
        this.terminationDate = terminationDate;
        this.revivalStatus = revivalStatus;
    }

    public static RevivalResponse of(CustomerInfoResponse customerInfoResponse, Revival revival) {
        int id = revival.getId();
        int productId = revival.getProductId();
        String terminationDate = revival.getTerminationDate();
        String revivalStatus = revival.getRevivalStatus().getText();
        return new RevivalResponse(id, customerInfoResponse, productId,
                terminationDate == null ? "" : terminationDate, revivalStatus);
    }
}
