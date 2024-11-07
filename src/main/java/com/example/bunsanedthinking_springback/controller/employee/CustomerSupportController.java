package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.model.service.employee.customerSupport.CustomerSupportSModel;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee/customerSupport")
public class CustomerSupportController {
	@Autowired
	private CustomerSupportSModel customerSupportSModel;

	@PatchMapping("/handleComplaint")
	public void handleComplaint(@RequestParam("employeeName") String employeeName, @RequestParam("complaintId") int complaintId,
			@RequestParam("result") String result) throws NotExistException, AlreadyProcessedException {
		customerSupportSModel.handleComplaint(employeeName, complaintId, result);
	}

	@PatchMapping("/handleAccident")
	public void handleAccident(@RequestParam("accidentId") int accidentId, @RequestParam("damageAssessmentCompanyId")int damageAssessmentCompanyId,
			@RequestParam("roadsideAssistanceCompanyId") int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		customerSupportSModel.handleAccident(accidentId, damageAssessmentCompanyId, roadsideAssistanceCompanyId);
	}

	@GetMapping("/getAllComplaint")
	public List<Complaint> getAllComplaint() {
		return customerSupportSModel.getAllComplaint();
	}

	@GetMapping("/getAllUnprocessedComplaint")
	public List<Complaint> getAllUnprocessedComplaint() {
		return customerSupportSModel.getAllUnprocessedComplaint();
	}

	@GetMapping("/getAllProcessedComplaint")
	public List<Complaint> getAllProcessedComplaint() {
		return customerSupportSModel.getAllProcessedComplaint();
	}

	@GetMapping("/getComplaint")
	public Complaint getComplaint(@RequestParam("complaintId") int complaintId) throws NotExistException {
		return customerSupportSModel.getComplaint(complaintId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerID) throws NotExistException {
		return customerSupportSModel.getCustomer(customerID);
	}

	@GetMapping("/getAllAccident")
	public List<Accident> getAllAccident() {
		return customerSupportSModel.getAllAccident();
	}

	@GetMapping("/getAllUnprocessedReport")
	public List<Accident> getAllUnprocessedReport() {
		return customerSupportSModel.getAllUnprocessedAccident();
	}

	@GetMapping("/getAllCompletedReport")
	public List<Accident> getAllCompletedReport() {
		return customerSupportSModel.getAllCompletedAccident();
	}

	@GetMapping("/getAccident")
	public Accident getAccident(@RequestParam("accidentId") int accidentId) throws NotExistException {
		return customerSupportSModel.getAccident(accidentId);
	}

	@GetMapping("/getAllRoadAssistanceCompany")
	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return customerSupportSModel.getAllRoadAssistanceCompany();
	}

	@GetMapping("/getRoadAssistanceCompany")
	public PartnerCompany getRoadAssistanceCompany(
			@RequestParam("roadAssistanceCompanyId") int roadAssistanceCompanyId) throws NotExistException {
		return customerSupportSModel.getRoadAssistanceCompany(roadAssistanceCompanyId);
	}

	@GetMapping("/getAllDamageAssessmentCompany")
	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return customerSupportSModel.getAllDamageAssessmentCompany();
	}

	@GetMapping("/getDamageAssessmentCompany")
	public PartnerCompany getDamageAssessmentCompany(
			@RequestParam("damageAssessmentCompanyId") int damageAssessmentCompanyId) throws NotExistException {
		return customerSupportSModel.getDamageAssessmentCompany(damageAssessmentCompanyId);
	}
}
