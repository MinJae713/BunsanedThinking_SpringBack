package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class SignUpDiseaseHistoryRequest {
	private String dateOfDiagnosis;
	private String name;

	public DiseaseHistory from(int customerId, int id) throws ParseException {
		DiseaseHistory diseaseHistory = new DiseaseHistory();
		diseaseHistory.setId(id);
		diseaseHistory.setCustomer_id(customerId);
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		diseaseHistory.setDate_of_diagnosis(formatter.parse(dateOfDiagnosis));
		diseaseHistory.setName(name);
		return diseaseHistory;
	}

	public boolean isNull() {
		return dateOfDiagnosis.isEmpty() || name.isEmpty();
	}
}

