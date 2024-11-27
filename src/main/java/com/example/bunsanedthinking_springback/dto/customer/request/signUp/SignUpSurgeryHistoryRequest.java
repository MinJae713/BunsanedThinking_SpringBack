package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class SignUpSurgeryHistoryRequest {
	private String date;
	private String hospitalName;
	private String name;

	public SurgeryHistory from(int customerId, int id) throws ParseException {
		SurgeryHistory surgeryHistory = new SurgeryHistory();
		surgeryHistory.setId(id);
		surgeryHistory.setCustomerID(customerId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		surgeryHistory.setDate(formatter.parse(date));
		surgeryHistory.setHospitalName(hospitalName);
		surgeryHistory.setName(name);
		return surgeryHistory;
	}

	public boolean isNull() {
		return date.isEmpty() || hospitalName.isEmpty() || name.isEmpty();
	}
}
