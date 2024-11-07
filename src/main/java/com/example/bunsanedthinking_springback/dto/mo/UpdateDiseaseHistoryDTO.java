package com.example.bunsanedthinking_springback.dto.mo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDiseaseHistoryDTO {
    private int id;
    private String date_of_diagnosis;
    private String name;
}
