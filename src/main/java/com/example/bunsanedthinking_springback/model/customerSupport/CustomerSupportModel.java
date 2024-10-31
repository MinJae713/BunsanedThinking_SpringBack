package com.example.bunsanedthinking_springback.model.customerSupport;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerSupportModel {

	public void handleComplaint(String employeeName, Complaint complaint, String result, ComplaintList complaintList) throws NotExistException, AlreadyProcessedException {
		if (complaint.getProcessStatus() == ComplaintProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 민원 처리가 완료되었습니다.");
		}
		// 로그인한 직원 이름이 여기 드감
		complaint.handle(employeeName, result);
		complaintList.update(complaint);
	}

	public void handleAccident(Accident accident, PartnerCompany damageAssessmentCompany,
							   PartnerCompany roadsideAssistanceCompany, ReportList reportList) throws AlreadyProcessedException {
		if (accident.getProcessStatus() == AccidentProcessStatus.Completed) {
			throw new AlreadyProcessedException("이미 신고 처리가 완료되었습니다.");
		} else if (accident.getProcessStatus() == AccidentProcessStatus.Processing) {
			throw new AlreadyProcessedException("신고 처리 중입니다.");
		}
		accident.processing();
		Report report = new Report(accident, damageAssessmentCompany, roadsideAssistanceCompany);
		reportList.add(report);
	}
	public ArrayList<Complaint> getAll(ComplaintList complaintList) {
		return complaintList.getAll();
	}
	public ArrayList<Complaint> getAllUnprocessedComplaint(ComplaintList complaintList) {
		return complaintList.getAllUnprocessedComplaint();
	}

	public ArrayList<Complaint> getAllProcessedComplant(ComplaintList complaintList) {
		return complaintList.getAllProcessedComplant();
	}

	public Complaint get(ComplaintList complaintList, int id) throws NotExistException {
		return complaintList.get(id);
	}

	public Customer get(CustomerList customerList, int customerID) throws NotExistException {
		return customerList.get(customerID);
	}

	public ArrayList<Accident> getAll(AccidentList accidentList) {
		return accidentList.getAll();
	}

	public ArrayList<Accident> getAllUnprocessedReport(AccidentList accidentList) {
		return accidentList.getAllUnprocessedReport();
	}

	public ArrayList<Accident> getAllCompletedReport(AccidentList accidentList) {
		return accidentList.getAllCompletedReport();
	}

	public Accident get(AccidentList accidentList, int id) throws NotExistException {
		return accidentList.get(id);
	}

	public ArrayList<PartnerCompany> getAllRoadAssistanceCompany(PartnerCompanyList partnerCompanyList) {
		return partnerCompanyList.getAllRoadAssistanceCompany();
	}

	public PartnerCompany getRoadAssistanceCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return partnerCompanyList.getRoadAssistanceCompany(id);
	}

	public ArrayList<PartnerCompany> getAllDamageAssessmentCompany(PartnerCompanyList partnerCompanyList) {
		return partnerCompanyList.getAllDamageAssessmentCompany();
	}

	public PartnerCompany getDamageAssessmentCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return partnerCompanyList.getDamageAssessmentCompany(id);
	}
}