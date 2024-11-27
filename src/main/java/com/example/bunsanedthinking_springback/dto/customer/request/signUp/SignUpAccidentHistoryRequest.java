package com.example.bunsanedthinking_springback.dto.customer.request.signUp;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class SignUpAccidentHistoryRequest {
	private String date;
	private String accidentDetail;

	public AccidentHistory from(int customerId, int id) throws ParseException {
		AccidentHistory accidentHistory = new AccidentHistory();
		accidentHistory.setId(id);
		accidentHistory.setCustomerID(customerId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		accidentHistory.setDate(formatter.parse(date));
		accidentHistory.setAccidentDetail(accidentDetail);
		return accidentHistory;
	}

	public boolean isNull() {
		return date.isEmpty() || accidentDetail.isEmpty();
	}
}
