package com.example.bunsanedthinking_springback.model.service.employee.customerSupport;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentDModel;
import com.example.bunsanedthinking_springback.model.entityModel.complaint.ComplaintDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyDModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportDModel;

@Service
public class CustomerSupportService {
	@Autowired
	private ComplaintDModel complaintDModel;
	@Autowired
	private PartnerCompanyDModel partnerCompanyDModel;
	@Autowired
	private AccidentDModel accidentDModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ReportDModel reportDModel;

	public void handleComplaint(String employeeName, int complaintId, String result) throws
		NotExistException,
		AlreadyProcessedException {
		Complaint complaint = complaintDModel.getById(complaintId);
		if (complaint == null)
			throw new NotExistException("해당하는 민원 정보를 찾을 수 없습니다.");
		if (complaint.getProcessStatus() == ComplaintProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 민원 처리가 완료되었습니다.");
		}

		complaint.setEmployeeName(employeeName);
		complaint.setResult(result);
		complaint.setProcessingDate(Date.valueOf(LocalDate.now()));
		complaint.setProcessStatus(ComplaintProcessStatus.Completed);
		complaintDModel.update(complaint);
	}

	public void handleAccident(int accidentId, int damageAssessmentCompanyId,
		int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		Accident accident = accidentDModel.getById(accidentId);
		if (accident == null) {
			return;
		}
		switch (accident.getProcessStatus()) {
			case Completed -> throw new AlreadyProcessedException("이미 신고 처리가 완료되었습니다.");
			case Processing -> throw new AlreadyProcessedException("신고 처리 중입니다.");
		}
		accident.setProcessStatus(AccidentProcessStatus.Processing);
		accidentDModel.update(accident);

		Report report = new Report(accident, null, accidentId, roadsideAssistanceCompanyId, damageAssessmentCompanyId,
			ReportProcessStatus.Unprocessed);
		reportDModel.add(report);
	}

	public List<Complaint> getAllComplaint() {
		return complaintDModel.getAll();
	}

	public List<Complaint> getAllUnprocessedComplaint() {
		return getAllByProcessStatus(ComplaintProcessStatus.Unprocessed);
	}

	public List<Complaint> getAllProcessedComplaint() {
		return getAllByProcessStatus(ComplaintProcessStatus.Completed);
	}

	private List<Complaint> getAllByProcessStatus(ComplaintProcessStatus processStatus) {
		List<Complaint> complaintList = complaintDModel.getAll();
		return complaintList.stream()
			.filter(complaint -> complaint.getProcessStatus() == processStatus)
			.collect(Collectors.toList());
	}

	public Complaint getComplaint(int id) throws NotExistException {
		Complaint complaint = complaintDModel.getById(id);
		if (complaint == null)
			throw new NotExistException("해당하는 민원 정보가 존재하지 않습니다.");
		return complaint;
	}

	public Customer getCustomer(int customerID) throws NotExistException {
		Customer customer = customerEntityModel.getById(customerID);
		if (customer == null)
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return customer;
	}

	public List<Accident> getAllAccident() {
		return accidentDModel.getAll();
	}

	public List<Accident> getAllUnprocessedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Unprocessed);
	}

	public List<Accident> getAllCompletedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Completed);
	}

	private List<Accident> getAllByProcessStatus(AccidentProcessStatus processStatus) {
		List<Accident> accidentList = accidentDModel.getAll();
		return accidentList.stream()
			.filter(accident -> accident.getProcessStatus() == processStatus)
			.collect(Collectors.toList());
	}

	public Accident getAccident(int id) throws NotExistException {
		Accident acciednt = accidentDModel.getById(id);
		if (acciednt == null)
			throw new NotExistException("해당하는 사고 정보가 존재하지 않습니다.");
		return acciednt;
	}

	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return partnerCompanyDModel.getAll_RoadsideCompany();
	}

	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return partnerCompanyDModel.getAll_DamageAssesmentCompany();
	}

	public PartnerCompany getRoadAssistanceCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyDModel.getById(id);
		if (partnerCompany == null)
			throw new NotExistException("해당하는 긴급 출동 업체 정보가 존재하지 않습니다.");
		return partnerCompany;
	}

	public PartnerCompany getDamageAssessmentCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyDModel.getById(id);
		if (partnerCompany == null)
			throw new NotExistException("해당하는 손해 사정 업체 정보가 존재하지 않습니다.");
		return partnerCompany;
	}
}
