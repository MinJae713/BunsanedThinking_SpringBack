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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/customerSupport")
public class CustomerSupportController {
	@Autowired
	private CustomerSupportModel customerSupportModel;

	@PatchMapping("/handleComplaint")
	public void handleComplaint(@RequestParam("employeeName") String employeeName, @RequestParam("complaintId") int complaintId,
			@RequestParam("result") String result) throws NotExistException, AlreadyProcessedException {
		customerSupportModel.handleComplaint(employeeName, complaintId, result);
	}

	@PatchMapping("/handleAccident")
	public void handleAccident(@RequestParam("accidentId") int accidentId, @RequestParam("damageAssessmentCompanyId")int damageAssessmentCompanyId,
			@RequestParam("roadsideAssistanceCompanyId") int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		customerSupportModel.handleAccident(accidentId, damageAssessmentCompanyId, roadsideAssistanceCompanyId);
	}

	@GetMapping("/getAllComplaint")
	public List<Complaint> getAllComplaint() {
		return customerSupportModel.getAllComplaint();
	}

	@GetMapping("/getAllUnprocessedComplaint")
	public List<Complaint> getAllUnprocessedComplaint() {
		return customerSupportModel.getAllUnprocessedComplaint();
	}

	@GetMapping("/getAllProcessedComplant")
	public List<Complaint> getAllProcessedComplant() {
		return customerSupportModel.getAllProcessedComplant();
	}

	@GetMapping("/getComplaint")
	public Complaint getComplaint(@RequestParam("complaintId") int complaintId) throws NotExistException {
		return customerSupportModel.getComplaint(complaintId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerID) throws NotExistException {
		return customerSupportModel.getCustomer(customerID);
	}

	@GetMapping("/getAllAccident")
	public List<Accident> getAllAccident() {
		return customerSupportModel.getAllAccident();
	}

	@GetMapping("/getAllUnprocessedReport")
	public List<Accident> getAllUnprocessedReport() {
		return customerSupportModel.getAllUnprocessedAccident();
	}

	@GetMapping("/getAllCompletedReport")
	public List<Accident> getAllCompletedReport() {
		return customerSupportModel.getAllCompletedAccident();
	}

	@GetMapping("/getAccident")
	public Accident getAccident(@RequestParam("accidentId") int accidentId) throws NotExistException {
		return customerSupportModel.getAccident(accidentId);
	}

	@GetMapping("/getAllRoadAssistanceCompany")
	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return customerSupportModel.getAllRoadAssistanceCompany();
	}

	@GetMapping("/getRoadAssistanceCompany")
	public PartnerCompany getRoadAssistanceCompany(
			@RequestParam("roadAssistanceCompanyId") int roadAssistanceCompanyId) throws NotExistException {
		return customerSupportModel.getRoadAssistanceCompany(roadAssistanceCompanyId);
	}

	@GetMapping("/getAllDamageAssessmentCompany")
	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return customerSupportModel.getAllDamageAssessmentCompany();
	}

	@GetMapping("/getDamageAssessmentCompany")
	public PartnerCompany getDamageAssessmentCompany(
			@RequestParam("damageAssessmentCompanyId") int damageAssessmentCompanyId) throws NotExistException {
		return customerSupportModel.getDamageAssessmentCompany(damageAssessmentCompanyId);
	}
}
