package com.example.bunsanedthinking_springback.dto.mo;

import com.example.bunsanedthinking_springback.dto.dae.AccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.dae.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.dae.SurgeryHistoryDTO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateCustomerInformationDTO {
    private int index;
    private String input;
    private int id; // 고객 ID
}
