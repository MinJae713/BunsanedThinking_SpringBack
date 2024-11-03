package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.customerSupport.CustomerSupportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/customerSupport")
public class CustomerSupportController {
	@Autowired
	private CustomerSupportModel customerSupportModel;

	public void handleComplaint(String employeeName, Complaint complaint, String result, ComplaintList complaintList) throws NotExistException, AlreadyProcessedException {
		customerSupportModel.handleComplaint(employeeName, complaint, result, complaintList);
	}

	public void handleAccident(Accident accident, PartnerCompany damageAssessmentCompany,
							   PartnerCompany roadsideAssistanceCompany, ReportList reportList) throws AlreadyProcessedException {
		customerSupportModel.handleAccident(accident, damageAssessmentCompany, roadsideAssistanceCompany, reportList);
	}
	public ArrayList<Complaint> getAll(ComplaintList complaintList) {
		return customerSupportModel.getAll(complaintList);
	}
	public ArrayList<Complaint> getAllUnprocessedComplaint(ComplaintList complaintList) {
		return customerSupportModel.getAllUnprocessedComplaint(complaintList);
	}
	public ArrayList<Complaint> getAllProcessedComplant(ComplaintList complaintList) {
		return customerSupportModel.getAllProcessedComplant(complaintList);
	}
	public Complaint get(ComplaintList complaintList, int id) throws NotExistException {
		return customerSupportModel.get(complaintList, id);
	}
	public Customer get(CustomerList customerList, int customerID) throws NotExistException {
		return customerSupportModel.get(customerList, customerID);
	}
	public ArrayList<Accident> getAll(AccidentList accidentList) {
		return customerSupportModel.getAll(accidentList);
	}
	public ArrayList<Accident> getAllUnprocessedReport(AccidentList accidentList) {
		return customerSupportModel.getAllUnprocessedAccident(accidentList);
	}
	public ArrayList<Accident> getAllCompletedReport(AccidentList accidentList) {
		return customerSupportModel.getAllCompletedAccident(accidentList);
	}
	public Accident get(AccidentList accidentList, int id) throws NotExistException {
		return customerSupportModel.get(accidentList, id);
	}
	public ArrayList<PartnerCompany> getAllRoadAssistanceCompany(PartnerCompanyList partnerCompanyList) {
		return customerSupportModel.getAllRoadAssistanceCompany(partnerCompanyList);
	}
	public PartnerCompany getRoadAssistanceCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return customerSupportModel.getRoadAssistanceCompany(partnerCompanyList, id);
	}
	public ArrayList<PartnerCompany> getAllDamageAssessmentCompany(PartnerCompanyList partnerCompanyList) {
		return customerSupportModel.getAllDamageAssessmentCompany(partnerCompanyList);
	}
	public PartnerCompany getDamageAssessmentCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return customerSupportModel.getDamageAssessmentCompany(partnerCompanyList, id);
	}
}
