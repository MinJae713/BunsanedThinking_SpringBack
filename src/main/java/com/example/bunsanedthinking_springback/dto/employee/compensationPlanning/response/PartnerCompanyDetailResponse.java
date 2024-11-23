package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerCompanyDetailResponse {
    private int id;
    private String name;
    private String phoneNumber;
    private int type;
    private String headName;
    private String headPhoneNumber;

    public static PartnerCompanyDetailResponse of(PartnerCompany partnerCompany) {
        int id = partnerCompany.getId();
        String name = partnerCompany.getName();
        String phoneNumber = partnerCompany.getPhoneNumber();
        int type = partnerCompany.getPartnerCompanyType().ordinal();
        String headName = partnerCompany.getHeadName();
        String headPhoneNumber = partnerCompany.getHeadPhoneNumber();
        return new PartnerCompanyDetailResponse(id, name, phoneNumber, type, headName, headPhoneNumber);
    }
}
