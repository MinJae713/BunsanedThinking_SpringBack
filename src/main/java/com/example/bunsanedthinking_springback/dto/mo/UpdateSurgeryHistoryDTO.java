package com.example.bunsanedthinking_springback.dto.mo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSurgeryHistoryDTO {
    private int id;
    private String date;
    private String hospitalName;
    private String name;
}
