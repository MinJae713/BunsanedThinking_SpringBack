package com.example.bunsanedthinking_springback.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.GetComplaintResponse;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.customerSupport.CustomerSupportService;

@RestController
@RequestMapping("/employee/customerSupport")
public class CustomerSupportController {
	@Autowired
	private CustomerSupportService customerSupportService;

	@PatchMapping("/handleComplaint")
	public void handleComplaint(@RequestParam("employeeName") String employeeName,
		@RequestParam("complaintId") int complaintId,
		@RequestParam("result") String result) throws NotExistException, AlreadyProcessedException {
		customerSupportService.handleComplaint(employeeName, complaintId, result);
	}

	@PatchMapping("/handleAccident")
	public void handleAccident(@RequestParam("accidentId") int accidentId,
		@RequestParam("damageAssessmentCompanyId") int damageAssessmentCompanyId,
		@RequestParam("roadsideAssistanceCompanyId") int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		customerSupportService.handleAccident(accidentId, damageAssessmentCompanyId, roadsideAssistanceCompanyId);
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		return customerSupportService.getEmployee(employeeId);
	}

	@GetMapping("/getAllComplaint")
	public List<GetComplaintResponse> getAllComplaint() {
		return customerSupportService.getAllComplaint();
	}

	@GetMapping("/getAllUnprocessedComplaint")
	public List<GetComplaintResponse> getAllUnprocessedComplaint() {
		return customerSupportService.getAllUnprocessedComplaint();
	}

	@GetMapping("/getAllProcessedComplaint")
	public List<GetComplaintResponse> getAllProcessedComplaint() {
		return customerSupportService.getAllProcessedComplaint();
	}

	@GetMapping("/getComplaint")
	public GetComplaintResponse getComplaint(@RequestParam("complaintId") int complaintId) throws NotExistException {
		return customerSupportService.getComplaint(complaintId);
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("customerId") int customerID) throws NotExistException {
		return customerSupportService.getCustomer(customerID);
	}

	@GetMapping("/getAllAccident")
	public List<Accident> getAllAccident() {
		return customerSupportService.getAllAccident();
	}

	@GetMapping("/getAllUnprocessedAccident")
	public List<Accident> getAllUnprocessedAccident() {
		return customerSupportService.getAllUnprocessedAccident();
	}

	@GetMapping("/getAllCompletedAccident")
	public List<Accident> getAllCompletedAccident() {
		return customerSupportService.getAllCompletedAccident();
	}

	@GetMapping("/getAllProcessingAccident")
	public List<Accident> getAllProcessingAccident() {
		return customerSupportService.getAllProcessingAccident();
	}

	@GetMapping("/getAccident")
	public Accident getAccident(@RequestParam("accidentId") int accidentId) throws NotExistException {
		return customerSupportService.getAccident(accidentId);
	}

	@GetMapping("/getAllRoadAssistanceCompany")
	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return customerSupportService.getAllRoadAssistanceCompany();
	}

	@GetMapping("/getRoadAssistanceCompany")
	public PartnerCompany getRoadAssistanceCompany(
		@RequestParam("roadAssistanceCompanyId") int roadAssistanceCompanyId) throws NotExistException {
		return customerSupportService.getRoadAssistanceCompany(roadAssistanceCompanyId);
	}

	@GetMapping("/getAllDamageAssessmentCompany")
	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return customerSupportService.getAllDamageAssessmentCompany();
	}

	@GetMapping("/getDamageAssessmentCompany")
	public PartnerCompany getDamageAssessmentCompany(
		@RequestParam("damageAssessmentCompanyId") int damageAssessmentCompanyId) throws NotExistException {
		return customerSupportService.getDamageAssessmentCompany(damageAssessmentCompanyId);
	}
}
