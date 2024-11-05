package com.example.bunsanedthinking_springback.dto.yoo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePartnerCompanyDTO {
    private int index;
    private String input;
    private int partnerCompanyId;
}
