package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetailVO {
    private int id;
    private String depositor_name;
    private LocalDate date;
    private int money;
    private int path;
    private int contract_id;

    public DepositDetail getEntity() {
        DepositDetail depositDetail = new DepositDetail();
        depositDetail.setId(id);
        depositDetail.setContractID(contract_id);
        depositDetail.setDate(Date.valueOf(date));
        depositDetail.setDepositorName(depositor_name);
        depositDetail.setMoney(money);
        depositDetail.setPath(DepositPath.values()[path]);
        return depositDetail;
    }
}
