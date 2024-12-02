package com.example.bunsanedthinking_springback.global.constants.serial;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "serials")
@Getter
@Setter
public class Serial {
    private int accident;
    private int accidentHistory;
    private int compensationDetail;
    private int complaint;
    private int contract;
    private int counsel;
    private int customer;
    private int department;
    private int depositDetail;
    private int diseaseHistory;
    private int employee;
    private int family;
    private int insurance;
    private int insuranceMoney;
    private int loan;
    private int officesupply;
    private int paymentDetail;
    private int partnercompany;
    private int product;
    private int report;
    private int surgeryHistory;
}
