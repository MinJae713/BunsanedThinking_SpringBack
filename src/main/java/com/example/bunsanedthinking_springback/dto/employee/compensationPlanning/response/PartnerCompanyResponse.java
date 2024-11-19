package com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerCompanyResponse {
    private int id;
    private String name;
    private String partnerCompanyType;
    private String phoneNumber;

    public static PartnerCompanyResponse of(PartnerCompany partnerCompany) {
        int id = partnerCompany.getId();
        String name = partnerCompany.getName();
        String partnerCompanyType = partnerCompany.getPartnerCompanyType().getName();
        String phoneNumber = partnerCompany.getPhoneNumber();
        return new PartnerCompanyResponse(id, name, partnerCompanyType, phoneNumber);
    }
}
