package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndorsementVO {
    private int contract_id;
    private LocalDate apply_date;
    private int endorsement_status;

    public EndorsementVO(int contract_id) {
        this.contract_id = contract_id;
    }

    public Endorsement getEntity(Contract contract) {
        Endorsement result = new Endorsement(contract);
        result.setApplyDate(Date.from(apply_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        result.setEndorsementStatus(EndorsementStatus.values()[endorsement_status]);
        return result;
    }
}
